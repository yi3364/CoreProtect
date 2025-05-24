package net.coreprotect.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.coreprotect.config.Config;
import net.coreprotect.config.ConfigHandler;
import net.coreprotect.database.Database;
import net.coreprotect.database.statement.UserStatement;

/**
 * 提供用于查询 CoreProtect 数据库中玩家会话数据的 API 方法。
 * 会话数据包括登录/退出事件及其对应的时间戳和位置。
 */
public class SessionLookup {

    /**
     * 用于标识会话相关事件的类型 ID。
     */
    public static final int ID = 0;

    /**
     * 私有构造方法，防止实例化。
     * 该工具类仅包含静态方法。
     */
    private SessionLookup() {
        throw new IllegalStateException("API 类");
    }

    /**
     * 查询指定用户的会话相关操作。
     * 返回指定时间范围内的登录和退出事件。
     * 
     * @param user
     *            要查询会话数据的用户名
     * @param offset
     *            时间范围（秒），0 表示不限制时间
     * @return 以 String 数组格式返回的结果列表，如 API 被禁用或无结果则返回空列表
     */
    public static List<String[]> performLookup(String user, int offset) {
        List<String[]> result = new ArrayList<>();

        if (!Config.getGlobal().API_ENABLED) {
            return result;
        }

        if (user == null) {
            return result;
        }

        try (Connection connection = Database.getConnection(false, 1000)) {
            if (connection == null) {
                return result;
            }

            // 准备查询参数
            String type = String.valueOf(ID);
            int time = (int) (System.currentTimeMillis() / 1000L);
            int checkTime = calculateCheckTime(time, offset);

            // 从缓存或数据库获取用户 ID
            int userId = getUserId(connection, user);

            // 查询数据库中的会话数据
            try (Statement statement = connection.createStatement()) {
                String query = buildSessionQuery(userId, checkTime);

                try (ResultSet results = statement.executeQuery(query)) {
                    while (results.next()) {
                        String[] sessionData = extractSessionData(connection, results, type);
                        result.add(sessionData);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 计算会话查询的时间阈值。
     * 
     * @param currentTime
     *            当前时间（秒）
     * @param offset
     *            时间偏移量（秒）
     * @return 计算后的时间阈值
     */
    private static int calculateCheckTime(int currentTime, int offset) {
        return (offset > 0) ? currentTime - offset : 0;
    }

    /**
     * 获取指定用户名对应的用户 ID。
     * 如果缓存中没有，则从数据库加载。
     * 
     * @param connection
     *            数据库连接
     * @param username
     *            要获取 ID 的用户名
     * @return 用户 ID
     */
    private static int getUserId(Connection connection, String username) {
        String lowerUsername = username.toLowerCase(Locale.ROOT);

        if (ConfigHandler.playerIdCache.get(lowerUsername) == null) {
            UserStatement.loadId(connection, username, null);
        }

        return ConfigHandler.playerIdCache.get(lowerUsername);
    }

    /**
     * 构建用于查询会话数据的 SQL 语句。
     * 
     * @param userId
     *            要查询的用户 ID
     * @param checkTime
     *            查询的时间阈值
     * @return SQL 查询语句
     */
    private static String buildSessionQuery(int userId, int checkTime) {
        return "SELECT time,user,wid,x,y,z,action FROM " + ConfigHandler.prefix + "session WHERE user = '" + userId + "' AND time > '" + checkTime + "' ORDER BY rowid DESC";
    }

    /**
     * 从结果集提取会话数据。
     * 
     * @param connection
     *            数据库连接
     * @param results
     *            要提取数据的结果集
     * @param type
     *            会话类型 ID
     * @return 会话数据数组
     * @throws Exception
     *             提取数据时发生错误
     */
    private static String[] extractSessionData(Connection connection, ResultSet results, String type) throws Exception {
        String resultTime = results.getString("time");
        int resultUserId = results.getInt("user");
        String resultWorldId = results.getString("wid");
        String resultX = results.getString("x");
        String resultY = results.getString("y");
        String resultZ = results.getString("z");
        String resultAction = results.getString("action");

        // 从缓存获取用户名，如无则加载
        if (ConfigHandler.playerIdCacheReversed.get(resultUserId) == null) {
            UserStatement.loadName(connection, resultUserId);
        }
        String resultUser = ConfigHandler.playerIdCacheReversed.get(resultUserId);

        // 创建并返回会话数据数组
        return new String[] { resultTime, resultUser, resultX, resultY, resultZ, resultWorldId, type, resultAction };
    }
}
