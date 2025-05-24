package net.coreprotect.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import net.coreprotect.config.Config;
import net.coreprotect.consumer.Consumer;
import net.coreprotect.consumer.Queue;
import net.coreprotect.consumer.process.Process;
import net.coreprotect.utility.MaterialUtils;
import net.coreprotect.utility.StringUtils;
import net.coreprotect.utility.WorldUtils;

/**
 * 提供用于查询处理队列中方块相关操作的 API 方法。
 * 本类允许检索尚未保存到数据库的操作。
 */
public class QueueLookup extends Queue {

    /**
     * 私有构造方法，防止实例化。
     * 该工具类仅包含静态方法。
     */
    private QueueLookup() {
        throw new IllegalStateException("API 类");
    }

    /**
     * 查询指定方块在处理队列中的相关操作。
     * 可用于获取尚未提交到数据库的操作。
     * 
     * @param block
     *            要在处理队列中查询的方块
     * @return 以 String 数组格式返回的结果列表，如 API 被禁用或无结果则返回空列表
     */
    public static List<String[]> performLookup(Block block) {
        List<String[]> result = new ArrayList<>();

        if (!Config.getGlobal().API_ENABLED) {
            return result;
        }

        if (block == null) {
            return result;
        }

        try {
            // 计算 consumer 队列中的操作总数
            int consumerCount = calculateConsumerCount();

            if (consumerCount == 0) {
                return result;
            }

            // 获取当前 consumer 的数据
            int currentConsumer = Consumer.currentConsumer;
            ArrayList<Object[]> consumerData = Consumer.consumer.get(currentConsumer);
            Map<Integer, String[]> users = Consumer.consumerUsers.get(currentConsumer);
            Map<Integer, Object> consumerObject = Consumer.consumerObjects.get(currentConsumer);

            // 当前方块位置，用于与队列中的操作进行比较
            Location blockLocation = block.getLocation();

            // 检查处理队列中的方块操作
            ListIterator<Object[]> iterator = consumerData.listIterator();
            while (iterator.hasNext()) {
                Object[] data = iterator.next();
                int id = (int) data[0];
                int action = (int) data[1];

                // 仅处理方块破坏和放置操作
                if (action != Process.BLOCK_BREAK && action != Process.BLOCK_PLACE) {
                    continue;
                }

                String[] userData = users.get(id);
                Object objectData = consumerObject.get(id);

                // 校验该操作是否与请求的方块有关
                if (isActionForBlock(userData, objectData, blockLocation)) {
                    Material blockType = (Material) data[2];
                    int legacyData = (int) data[3];
                    String blockData = (String) data[7];
                    String user = userData[0];
                    BlockState blockState = (BlockState) objectData;
                    Location location = blockState.getLocation();
                    int worldId = WorldUtils.getWorldId(location.getWorld().getName());
                    int resultType = MaterialUtils.getBlockId(blockType);
                    int time = (int) (System.currentTimeMillis() / 1000L);

                    String[] lookupData = new String[] { String.valueOf(time), user, String.valueOf(location.getBlockX()), String.valueOf(location.getBlockY()), String.valueOf(location.getBlockZ()), String.valueOf(resultType), String.valueOf(legacyData), String.valueOf(action), "0", String.valueOf(worldId), blockData };

                    result.add(StringUtils.toStringArray(lookupData));
                }
            }

            // 反转结果列表，使其与数据库查询顺序一致（最新在前）
            Collections.reverse(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 计算 consumer 队列中的操作总数。
     * 
     * @return consumer 队列中的操作总数
     */
    private static int calculateConsumerCount() {
        int currentConsumerSize = Process.getCurrentConsumerSize();
        if (currentConsumerSize == 0) {
            return Consumer.getConsumerSize(0) + Consumer.getConsumerSize(1);
        }
        else {
            int consumerId = (Consumer.currentConsumer == 1) ? 1 : 0;
            return Consumer.getConsumerSize(consumerId) + currentConsumerSize;
        }
    }

    /**
     * 判断队列中的操作是否与指定方块位置相关。
     * 
     * @param userData
     *            操作关联的用户数据
     * @param objectData
     *            操作关联的对象数据
     * @param blockLocation
     *            被查询方块的位置
     * @return 若操作与指定方块相关则返回 true，否则返回 false
     */
    private static boolean isActionForBlock(String[] userData, Object objectData, Location blockLocation) {
        return userData != null && objectData != null && (objectData instanceof BlockState) && ((BlockState) objectData).getLocation().equals(blockLocation);
    }
}
