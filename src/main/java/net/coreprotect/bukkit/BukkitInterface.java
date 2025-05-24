package net.coreprotect.bukkit;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Bukkit API 兼容接口，适配不同 Minecraft 版本。
 * 每个方法都提供了针对不同 Minecraft/Bukkit API 版本的实现。
 */
public interface BukkitInterface {

    // --------------------------------------------------------------------------
    // 方块相关方法
    // --------------------------------------------------------------------------

    /**
     * 检查一个方块是否依附于另一个方块。
     * 
     * @param block
     *            基础方块
     * @param scanBlock
     *            要检查依附关系的方块
     * @param blockData
     *            方块数据
     * @param scanMin
     *            最小扫描值
     * @return 如果依附则返回 true，否则返回 false
     */
    boolean isAttached(Block block, Block scanBlock, BlockData blockData, int scanMin);

    /**
     * 获取世界的最小高度。
     * 
     * @param world
     *            世界
     * @return 最小高度
     */
    int getMinHeight(World world);

    /**
     * 获取指定材质的旧版方块 ID。
     * 
     * @param material
     *            材质
     * @return 旧版方块 ID，不适用时返回 -1
     */
    int getLegacyBlockId(Material material);

    /**
     * 获取桶内的内容材质。
     * 
     * @param material
     *            桶材质
     * @return 桶内的材质，不适用时返回 AIR
     */
    Material getBucketContents(Material material);

    // --------------------------------------------------------------------------
    // 材质类型判断方法
    // --------------------------------------------------------------------------

    /**
     * 判断材质是否为物品展示框。
     * 
     * @param material
     *            要检查的材质
     * @return 是物品展示框返回 true，否则返回 false
     */
    boolean isItemFrame(Material material);

    /**
     * 判断材质是否为隐形。
     * 
     * @param material
     *            要检查的材质
     * @return 隐形返回 true，否则返回 false
     */
    boolean isInvisible(Material material);

    /**
     * 判断材质是否为装饰陶罐。
     * 
     * @param material
     *            要检查的材质
     * @return 是装饰陶罐返回 true，否则返回 false
     */
    boolean isDecoratedPot(Material material);

    /**
     * 判断材质是否为可疑方块。
     * 
     * @param material
     *            要检查的材质
     * @return 是可疑方块返回 true，否则返回 false
     */
    boolean isSuspiciousBlock(Material material);

    /**
     * 判断材质是否为告示牌。
     * 
     * @param material
     *            要检查的材质
     * @return 是告示牌返回 true，否则返回 false
     */
    boolean isSign(Material material);

    /**
     * 判断材质是否为雕纹书架。
     * 
     * @param material
     *            要检查的材质
     * @return 是雕纹书架返回 true，否则返回 false
     */
    boolean isChiseledBookshelf(Material material);

    /**
     * 判断材质是否为书架上的书。
     * 
     * @param material
     *            要检查的材质
     * @return 是书架书返回 true，否则返回 false
     */
    boolean isBookshelfBook(Material material);

    /**
     * 获取植物材质对应的种子材质。
     * 
     * @param material
     *            植物材质
     * @return 种子材质
     */
    Material getPlantSeeds(Material material);

    // --------------------------------------------------------------------------
    // 物品与背包相关方法
    // --------------------------------------------------------------------------

    /**
     * 调整商人配方中的原材料。
     * 
     * @param recipe
     *            商人配方
     * @param itemStack
     *            物品堆
     * @return 调整后的物品堆，不适用时返回 null
     */
    ItemStack adjustIngredient(MerchantRecipe recipe, ItemStack itemStack);

    /**
     * 从物品元数据中获取信息。
     * 
     * @param itemMeta
     *            物品元数据
     * @param list
     *            用于填充元数据的列表
     * @param metadata
     *            用于填充的元数据列表
     * @param slot
     *            槽位
     * @return 获取成功返回 true，否则返回 false
     */
    boolean getItemMeta(ItemMeta itemMeta, List<Map<String, Object>> list, List<List<Map<String, Object>>> metadata, int slot);

    /**
     * 设置物品堆的元数据。
     * 
     * @param rowType
     *            材质类型
     * @param itemstack
     *            物品堆
     * @param map
     *            元数据映射
     * @return 设置成功返回 true，否则返回 false
     */
    boolean setItemMeta(Material rowType, ItemStack itemstack, List<Map<String, Object>> map);

    /**
     * 从雕纹书架获取书本。
     * 
     * @param blockState
     *            方块状态
     * @param event
     *            玩家交互事件
     * @return 书本物品堆，不适用时返回 null
     */
    ItemStack getChiseledBookshelfBook(BlockState blockState, PlayerInteractEvent event);

    /**
     * 获取箭矢的元数据。
     * 
     * @param arrow
     *            箭实体
     * @param itemStack
     *            物品堆
     * @return 带有箭矢元数据的物品堆
     */
    ItemStack getArrowMeta(Arrow arrow, ItemStack itemStack);

    // --------------------------------------------------------------------------
    // 实体相关方法
    // --------------------------------------------------------------------------

    /**
     * 获取生物实体的元数据。
     * 
     * @param entity
     *            生物实体
     * @param info
     *            用于填充实体元数据的列表
     * @return 获取成功返回 true，否则返回 false
     */
    boolean getEntityMeta(LivingEntity entity, List<Object> info);

    /**
     * 设置实体的元数据。
     * 
     * @param entity
     *            实体
     * @param value
     *            元数据值
     * @param count
     *            数量
     * @return 设置成功返回 true，否则返回 false
     */
    boolean setEntityMeta(Entity entity, Object value, int count);

    /**
     * 获取狼的变种信息，并添加到 info 列表。
     * 仅在 Minecraft 1.21+ 实现。
     * 
     * @param wolf
     *            狼实体
     * @param info
     *            用于添加变种信息的列表
     */
    void getWolfVariant(org.bukkit.entity.Wolf wolf, List<Object> info);

    /**
     * 设置狼的变种信息。
     * 仅在 Minecraft 1.21+ 实现。
     * 
     * @param wolf
     *            狼实体
     * @param value
     *            要设置的变种值
     */
    void setWolfVariant(org.bukkit.entity.Wolf wolf, Object value);

    /**
     * 获取实体的展示框类型。
     * 
     * @param entity
     *            实体
     * @return 展示框材质类型
     */
    Material getFrameType(Entity entity);

    /**
     * 获取实体类型对应的展示框材质。
     * 
     * @param type
     *            实体类型
     * @return 展示框材质类型
     */
    Material getFrameType(EntityType type);

    /**
     * 获取材质对应的实体类型。
     * 
     * @param material
     *            材质
     * @return 实体类型
     */
    EntityType getEntityType(Material material);

    /**
     * 获取材质对应的展示框类。
     * 
     * @param material
     *            材质
     * @return 展示框类
     */
    Class<?> getFrameClass(Material material);

    // --------------------------------------------------------------------------
    // 告示牌相关方法
    // --------------------------------------------------------------------------

    /**
     * 检查告示牌是否为发光告示牌。
     * 
     * @param sign
     *            告示牌
     * @param isFront
     *            是否检查正面
     * @return 发光返回 true，否则返回 false
     */
    boolean isGlowing(Sign sign, boolean isFront);

    /**
     * 检查告示牌是否为打蜡告示牌。
     * 
     * @param sign
     *            告示牌
     * @return 打蜡返回 true，否则返回 false
     */
    boolean isWaxed(Sign sign);

    /**
     * 设置告示牌是否为发光告示牌。
     * 
     * @param sign
     *            告示牌
     * @param isFront
     *            是否设置正面
     * @param isGlowing
     *            是否发光
     */
    void setGlowing(Sign sign, boolean isFront, boolean isGlowing);

    /**
     * 设置告示牌颜色。
     * 
     * @param sign
     *            告示牌
     * @param isFront
     *            是否设置正面颜色
     * @param color
     *            颜色 RGB 值
     */
    void setColor(Sign sign, boolean isFront, int color);

    /**
     * 设置告示牌是否为打蜡告示牌。
     * 
     * @param sign
     *            告示牌
     * @param isWaxed
     *            是否打蜡
     */
    void setWaxed(Sign sign, boolean isWaxed);

    /**
     * 获取告示牌颜色。
     * 
     * @param sign
     *            告示牌
     * @param isFront
     *            是否获取正面颜色
     * @return 颜色 RGB 值
     */
    int getColor(Sign sign, boolean isFront);

    /**
     * 获取告示牌某一行的文本。
     * 
     * @param sign
     *            告示牌
     * @param line
     *            行号（从 0 开始）
     * @return 该行文本
     */
    String getLine(Sign sign, int line);

    /**
     * 设置告示牌某一行的文本。
     * 
     * @param sign
     *            告示牌
     * @param line
     *            行号（从 0 开始）
     * @param string
     *            要设置的文本
     */
    void setLine(Sign sign, int line, String string);

    /**
     * 检查告示牌变更事件是否为正面。
     * 
     * @param event
     *            告示牌变更事件
     * @return 正面返回 true，否则返回 false
     */
    boolean isSignFront(SignChangeEvent event);

    // --------------------------------------------------------------------------
    // 注册表相关方法
    // --------------------------------------------------------------------------

    /**
     * 获取值的注册表键。
     * 
     * @param value
     *            值
     * @return 注册表键
     */
    Object getRegistryKey(Object value);

    /**
     * 通过键和类获取注册表值。
     * 
     * @param key
     *            键
     * @param tClass
     *            类
     * @return 注册表值
     */
    Object getRegistryValue(String key, Object tClass);

    /**
     * 解析旧版材质名称。
     * 
     * @param name
     *            旧版名称
     * @return 解析后的名称
     */
    String parseLegacyName(String name);
}
