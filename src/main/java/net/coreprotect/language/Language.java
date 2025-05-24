package net.coreprotect.language;

import java.util.concurrent.ConcurrentHashMap;

public class Language {

    private static ConcurrentHashMap<Phrase, String> phrases = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Phrase, String> userPhrases = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Phrase, String> translatedPhrases = new ConcurrentHashMap<>();

    protected static String getPhrase(Phrase phrase) {
        return phrases.get(phrase);
    }

    protected static String getUserPhrase(Phrase phrase) {
        return userPhrases.get(phrase);
    }

    protected static String getTranslatedPhrase(Phrase phrase) {
        return translatedPhrases.get(phrase);
    }

    protected static void setUserPhrase(Phrase phrase, String value) {
        userPhrases.put(phrase, value);
    }

    protected static void setTranslatedPhrase(Phrase phrase, String value) {
        translatedPhrases.put(phrase, value);
    }

    public static void loadPhrases() {
        phrases.put(Phrase.ACTION_NOT_SUPPORTED, "该命令不支持此操作。");
        phrases.put(Phrase.AMOUNT_BLOCK, "{0} 个方块");
        phrases.put(Phrase.AMOUNT_CHUNK, "{0} 个区块");
        phrases.put(Phrase.AMOUNT_ENTITY, "{0} 个实体");
        phrases.put(Phrase.AMOUNT_ITEM, "{0} 个物品");
        phrases.put(Phrase.API_TEST, "API 测试成功。");
        phrases.put(Phrase.CACHE_ERROR, "警告：验证 {0} 缓存时出错。");
        phrases.put(Phrase.CACHE_RELOAD, "强制从数据库重新加载 {mapping|world} 缓存。");
        phrases.put(Phrase.CHECK_CONFIG, "请检查 config.yml 配置文件");
        phrases.put(Phrase.COMMAND_CONSOLE, "请在控制台中运行该命令。");
        phrases.put(Phrase.COMMAND_NOT_FOUND, "未找到命令 \"{0}\"。");
        phrases.put(Phrase.COMMAND_THROTTLED, "请稍后再试。");
        phrases.put(Phrase.CONSUMER_ERROR, "消费者队列处理已被 {暂停|恢复}。");
        phrases.put(Phrase.CONSUMER_TOGGLED, "消费者队列处理已被 {暂停|恢复}。");
        phrases.put(Phrase.CONTAINER_HEADER, "容器交易");
        phrases.put(Phrase.CPU_CORES, "CPU 核心数。");
        phrases.put(Phrase.DATABASE_BUSY, "数据库繁忙，请稍后再试。");
        phrases.put(Phrase.DATABASE_INDEX_ERROR, "无法验证数据库索引。");
        phrases.put(Phrase.DATABASE_LOCKED_1, "数据库已锁定。最多等待 15 秒...");
        phrases.put(Phrase.DATABASE_LOCKED_2, "数据库已被占用，请稍后再试。");
        phrases.put(Phrase.DATABASE_LOCKED_3, "要禁用数据库锁定，请设置 \"database-lock: false\"。");
        phrases.put(Phrase.DATABASE_LOCKED_4, "禁用数据库锁定可能导致数据损坏。");
        phrases.put(Phrase.DATABASE_UNREACHABLE, "数据库无法访问。正在丢弃数据并关闭。");
        phrases.put(Phrase.DEVELOPMENT_BRANCH, "检测到开发分支，跳过补丁脚本。");
        phrases.put(Phrase.DIRT_BLOCK, "在你脚下放置了一个临时安全方块。");
        phrases.put(Phrase.DISABLE_SUCCESS, "成功！已禁用 {0}");
        phrases.put(Phrase.DONATION_KEY_REQUIRED, "该命令需要有效的捐赠密钥。");
        phrases.put(Phrase.ENABLE_FAILED, "{0} 启动失败。");
        phrases.put(Phrase.ENABLE_SUCCESS, "{0} 已成功启用！");
        phrases.put(Phrase.ENJOY_COREPROTECT, "喜欢 {0} 吗？欢迎加入我们的 Discord！");
        phrases.put(Phrase.FINISHING_CONVERSION, "正在完成数据转换，请稍候...");
        phrases.put(Phrase.FINISHING_LOGGING, "正在完成数据记录，请稍候...");
        phrases.put(Phrase.FIRST_VERSION, "初始数据库版本：{0}");
        phrases.put(Phrase.GLOBAL_LOOKUP, "不指定半径将进行全局查询。");
        phrases.put(Phrase.GLOBAL_ROLLBACK, "使用 \"{0}\" 进行全局 {回滚|还原}");
        phrases.put(Phrase.HELP_ACTION_1, "限制查询为特定操作。");
        phrases.put(Phrase.HELP_ACTION_2, "示例: [a:block], [a:+block], [a:-block] [a:click], [a:container], [a:inventory], [a:item], [a:kill], [a:chat], [a:command], [a:sign], [a:session], [a:username]");
        phrases.put(Phrase.HELP_COMMAND, "显示该命令的更多信息。");
        phrases.put(Phrase.HELP_EXCLUDE_1, "排除方块/玩家。");
        phrases.put(Phrase.HELP_EXCLUDE_2, "示例: [e:stone], [e:Notch], [e:stone,Notch]");
        phrases.put(Phrase.HELP_HEADER, "{0} 帮助");
        phrases.put(Phrase.HELP_INCLUDE_1, "包含特定方块/实体。");
        phrases.put(Phrase.HELP_INCLUDE_2, "示例: [i:stone], [i:zombie], [i:stone,wood,bedrock]");
        phrases.put(Phrase.HELP_INSPECT_1, "启用检查器后，你可以执行以下操作：");
        phrases.put(Phrase.HELP_INSPECT_2, "左键点击方块查看谁放置了该方块。");
        phrases.put(Phrase.HELP_INSPECT_3, "右键点击方块查看相邻方块被谁破坏。");
        phrases.put(Phrase.HELP_INSPECT_4, "放置方块查看该位置之前被破坏的方块。");
        phrases.put(Phrase.HELP_INSPECT_5, "在液体等处放置方块查看是谁放置的。");
        phrases.put(Phrase.HELP_INSPECT_6, "右键点击门、箱子等查看最后使用者。");
        phrases.put(Phrase.HELP_INSPECT_7, "提示：你可以只输入 \"/co i\" 快速访问。");
        phrases.put(Phrase.HELP_INSPECT_COMMAND, "开启或关闭方块检查器。");
        phrases.put(Phrase.HELP_LIST, "显示所有命令列表。");
        phrases.put(Phrase.HELP_LOOKUP_1, "命令快捷方式。");
        phrases.put(Phrase.HELP_LOOKUP_2, "检查方块后使用以查看日志。");
        phrases.put(Phrase.HELP_LOOKUP_COMMAND, "高级方块数据查询。");
        phrases.put(Phrase.HELP_NO_INFO, "未找到命令 \"{0}\" 的信息。");
        phrases.put(Phrase.HELP_PARAMETER, "请参阅 \"{0}\" 获取详细参数信息。");
        phrases.put(Phrase.HELP_PARAMS_1, "执行 {查询|回滚|还原}。");
        phrases.put(Phrase.HELP_PARAMS_2, "指定要 {查询|回滚|还原} 的用户。");
        phrases.put(Phrase.HELP_PARAMS_3, "指定要 {查询|回滚|还原} 的时间。");
        phrases.put(Phrase.HELP_PARAMS_4, "指定半径区域以限制 {查询|回滚|还原}。");
        phrases.put(Phrase.HELP_PARAMS_5, "限制 {查询|回滚|还原} 为特定操作。");
        phrases.put(Phrase.HELP_PARAMS_6, "在 {查询|回滚|还原} 中包含特定方块/实体。");
        phrases.put(Phrase.HELP_PARAMS_7, "在 {查询|回滚|还原} 中排除方块/用户。");
        phrases.put(Phrase.HELP_PURGE_1, "删除早于指定时间的数据。");
        phrases.put(Phrase.HELP_PURGE_2, "例如，\"{0}\" 将删除所有早于一个月的数据，仅保留最近 30 天的数据。");
        phrases.put(Phrase.HELP_PURGE_COMMAND, "删除旧的方块数据。");
        phrases.put(Phrase.HELP_RADIUS_1, "指定半径区域。");
        phrases.put(Phrase.HELP_RADIUS_2, "示例: [r:10]（仅在距离你 10 格内进行更改）");
        phrases.put(Phrase.HELP_RELOAD_COMMAND, "重新加载配置文件。");
        phrases.put(Phrase.HELP_RESTORE_COMMAND, "还原方块数据。");
        phrases.put(Phrase.HELP_ROLLBACK_COMMAND, "回滚方块数据。");
        phrases.put(Phrase.HELP_STATUS, "查看插件状态和版本信息。");
        phrases.put(Phrase.HELP_STATUS_COMMAND, "显示插件状态。");
        phrases.put(Phrase.HELP_TELEPORT, "传送到指定位置。");
        phrases.put(Phrase.HELP_TIME_1, "指定要查询的时间。");
        phrases.put(Phrase.HELP_TIME_2, "示例: [t:2w,5d,7h,2m,10s], [t:5d2h], [t:2.50h]");
        phrases.put(Phrase.HELP_USER_1, "指定要查询的用户。");
        phrases.put(Phrase.HELP_USER_2, "示例: [u:Notch], [u:Notch,#enderman]");
        phrases.put(Phrase.INCOMPATIBLE_ACTION, "\"{0}\" 不能与该操作一起使用。");
        phrases.put(Phrase.INSPECTOR_ERROR, "检查器已被 {启用|禁用}。");
        phrases.put(Phrase.INSPECTOR_TOGGLED, "检查器已被 {启用|禁用}。");
        phrases.put(Phrase.INTEGRATION_ERROR, "无法 {初始化|禁用} {0} 日志记录。");
        phrases.put(Phrase.INTEGRATION_SUCCESS, "{0} 日志记录已成功 {初始化|禁用}。");
        phrases.put(Phrase.INTEGRATION_VERSION, "检测到无效的 {0} 版本。");
        phrases.put(Phrase.INTERACTIONS_HEADER, "玩家交互");
        phrases.put(Phrase.INVALID_ACTION, "无效的操作。");
        phrases.put(Phrase.INVALID_BRANCH_1, "插件版本无效（分支未设置）。");
        phrases.put(Phrase.INVALID_BRANCH_2, "要继续，请将项目分支设置为 \"development\"。");
        phrases.put(Phrase.INVALID_BRANCH_3, "运行开发代码可能导致数据损坏。");
        phrases.put(Phrase.INVALID_CONTAINER, "请先检查有效的容器。");
        phrases.put(Phrase.INVALID_DONATION_KEY, "捐赠密钥无效。");
        phrases.put(Phrase.INVALID_INCLUDE, "\"{0}\" 不是有效的方块/实体名称。");
        phrases.put(Phrase.INVALID_INCLUDE_COMBO, "无效的方块/实体组合。");
        phrases.put(Phrase.INVALID_RADIUS, "请输入有效的半径。");
        phrases.put(Phrase.INVALID_SELECTION, "未找到 {0} 选择。");
        phrases.put(Phrase.INVALID_USERNAME, "\"{0}\" 不是有效的用户名。");
        phrases.put(Phrase.INVALID_WORLD, "请指定有效的世界。");
        phrases.put(Phrase.LATEST_VERSION, "最新版本：{0}");
        phrases.put(Phrase.LINK_DISCORD, "Discord: {0}");
        phrases.put(Phrase.LINK_DOWNLOAD, "下载: {0}");
        phrases.put(Phrase.LINK_PATREON, "Patreon: {0}");
        phrases.put(Phrase.LINK_WIKI_BLOCK, "方块名称: {0}");
        phrases.put(Phrase.LINK_WIKI_ENTITY, "实体名称: {0}");
        phrases.put(Phrase.LOGGING_ITEMS, "还有 {0} 个项目待记录，请稍候...");
        phrases.put(Phrase.LOGGING_TIME_LIMIT, "记录时间已达上限。正在丢弃数据并关闭。");
        phrases.put(Phrase.LOOKUP_BLOCK, "{0} {放置|破坏} 了 {1}。");
        phrases.put(Phrase.LOOKUP_CONTAINER, "{0} {添加|移除} 了 {1} {2}。");
        phrases.put(Phrase.LOOKUP_HEADER, "{0} 查询结果");
        phrases.put(Phrase.LOOKUP_INTERACTION, "{0} {点击|击杀} 了 {1}。");
        phrases.put(Phrase.LOOKUP_ITEM, "{0} {捡起|丢弃} 了 {1} {2}。");
        phrases.put(Phrase.LOOKUP_LOGIN, "{0} 已{上线|下线}。");
        phrases.put(Phrase.LOOKUP_PAGE, "第 {0} 页");
        phrases.put(Phrase.LOOKUP_PROJECTILE, "{0} {投掷|射击} 了 {1} {2}。");
        phrases.put(Phrase.LOOKUP_ROWS_FOUND, "找到 {0} 条记录。");
        phrases.put(Phrase.LOOKUP_SEARCHING, "正在查询，请稍候...");
        phrases.put(Phrase.LOOKUP_STORAGE, "{0} {存入|取出} 了 {1} {2}。");
        phrases.put(Phrase.LOOKUP_TIME, "{0} 前");
        phrases.put(Phrase.LOOKUP_USERNAME, "{0} 登录名为 {1}。");
        phrases.put(Phrase.MAXIMUM_RADIUS, "最大 {查询|回滚|还原} 半径为 {0}。");
        phrases.put(Phrase.MISSING_ACTION_USER, "要使用该操作，请指定用户。");
        phrases.put(Phrase.MISSING_LOOKUP_TIME, "请指定 {查询|回滚|还原} 的时间。");
        phrases.put(Phrase.MISSING_LOOKUP_USER, "请指定要查询的用户或 {方块|半径}。");
        phrases.put(Phrase.MISSING_PARAMETERS, "请使用 \"{0}\"。");
        phrases.put(Phrase.MISSING_ROLLBACK_RADIUS, "你未指定 {回滚|还原} 半径。");
        phrases.put(Phrase.MISSING_ROLLBACK_USER, "你未指定 {回滚|还原} 用户。");
        phrases.put(Phrase.MYSQL_UNAVAILABLE, "无法连接到 MySQL 服务器。");
        phrases.put(Phrase.NETWORK_CONNECTION, "{0} 连接 {成功|失败}。使用 {1} {2}。");
        phrases.put(Phrase.NETWORK_TEST, "网络测试数据已成功发送。");
        phrases.put(Phrase.NO_DATA, "在 {0} 未找到数据。");
        phrases.put(Phrase.NO_DATA_LOCATION, "该位置未找到 {数据|交易|交互|消息}。");
        phrases.put(Phrase.NO_PERMISSION, "你没有权限执行此操作。");
        phrases.put(Phrase.NO_RESULTS, "未找到结果。");
        phrases.put(Phrase.NO_RESULTS_PAGE, "该页未找到 {结果|数据}。");
        phrases.put(Phrase.NO_ROLLBACK, "未找到 {待处理|之前} 的回滚/还原。");
        phrases.put(Phrase.PATCH_INTERRUPTED, "升级中断。将在重启时重试。");
        phrases.put(Phrase.PATCH_OUTDATED_1, "无法升级早于 {0} 的数据库。");
        phrases.put(Phrase.PATCH_OUTDATED_2, "请使用支持的 CoreProtect 版本进行升级。");
        phrases.put(Phrase.PATCH_PROCESSING, "正在处理新数据，请稍候...");
        phrases.put(Phrase.PATCH_SKIP_UPDATE, "跳过 {0} 上的 {表|索引} {更新|创建|移除}。");
        phrases.put(Phrase.PATCH_STARTED, "正在执行 {0} 升级，请稍候...");
        phrases.put(Phrase.PATCH_SUCCESS, "成功升级到 {0}。");
        phrases.put(Phrase.PATCH_UPGRADING, "数据库升级中，请稍候...");
        phrases.put(Phrase.PLEASE_SELECT, "请选择：\"{0}\" 或 \"{1}\"。");
        phrases.put(Phrase.PREVIEW_CANCELLED, "预览已取消。");
        phrases.put(Phrase.PREVIEW_CANCELLING, "正在取消预览...");
        phrases.put(Phrase.PREVIEW_IN_GAME, "只能在游戏内预览回滚。");
        phrases.put(Phrase.PREVIEW_TRANSACTION, "无法预览 {容器|背包} 交易。");
        phrases.put(Phrase.PRIMARY_THREAD_ERROR, "该 API 方法不能在主线程中使用。");
        phrases.put(Phrase.PURGE_ABORTED, "清理失败。数据库可能已损坏。");
        phrases.put(Phrase.PURGE_ERROR, "无法处理 {0} 数据！");
        phrases.put(Phrase.PURGE_FAILED, "清理失败，请稍后再试。");
        phrases.put(Phrase.PURGE_IN_PROGRESS, "正在清理，请稍后再试。");
        phrases.put(Phrase.PURGE_MINIMUM_TIME, "只能清理早于 {0} {天|小时} 的数据。");
        phrases.put(Phrase.PURGE_NOTICE_1, "请注意，这可能需要一些时间。");
        phrases.put(Phrase.PURGE_NOTICE_2, "完成前请勿重启服务器。");
        phrases.put(Phrase.PURGE_OPTIMIZING, "正在优化数据库，请稍候...");
        phrases.put(Phrase.PURGE_PROCESSING, "正在处理 {0} 数据...");
        phrases.put(Phrase.PURGE_REPAIRING, "正在尝试修复，这可能需要一些时间...");
        phrases.put(Phrase.PURGE_ROWS, "已删除 {0} 条数据。");
        phrases.put(Phrase.PURGE_STARTED, "已开始在 \"{0}\" 上清理数据。");
        phrases.put(Phrase.PURGE_SUCCESS, "数据清理成功。");
        phrases.put(Phrase.RAM_STATS, "{0}GB / {1}GB 内存");
        phrases.put(Phrase.RELOAD_STARTED, "正在重新加载配置，请稍候。");
        phrases.put(Phrase.RELOAD_SUCCESS, "配置已成功重新加载。");
        phrases.put(Phrase.ROLLBACK_ABORTED, "回滚或还原已中止。");
        phrases.put(Phrase.ROLLBACK_CHUNKS_FOUND, "找到 {0} 个区块需要修改。");
        phrases.put(Phrase.ROLLBACK_CHUNKS_MODIFIED, "已修改 {0}/{1} 个区块。");
        phrases.put(Phrase.ROLLBACK_COMPLETED, "\"{0}\" 的 {回滚|还原|预览} 已完成。");
        phrases.put(Phrase.ROLLBACK_EXCLUDED_USERS, "排除的用户：\"{0}\"。");
        phrases.put(Phrase.ROLLBACK_INCLUDE, "{包含|排除} 的 {方块|实体|目标} 类型：\"{0}\"。");
        phrases.put(Phrase.ROLLBACK_IN_PROGRESS, "已有回滚/还原正在进行中。");
        phrases.put(Phrase.ROLLBACK_LENGTH, "耗时：{0} 秒。");
        phrases.put(Phrase.ROLLBACK_MODIFIED, "{已修改|正在修改} {0}。");
        phrases.put(Phrase.ROLLBACK_RADIUS, "半径：{0} 个方块。");
        phrases.put(Phrase.ROLLBACK_SELECTION, "半径已设置为 \"{0}\"。");
        phrases.put(Phrase.ROLLBACK_STARTED, "\"{0}\" 的 {回滚|还原|预览} 已开始。");
        phrases.put(Phrase.ROLLBACK_TIME, "时间范围：{0}。");
        phrases.put(Phrase.ROLLBACK_WORLD_ACTION, "限制为世界/操作 \"{0}\"。");
        phrases.put(Phrase.SIGN_HEADER, "告示牌消息");
        phrases.put(Phrase.STATUS_CONSUMER, "队列中有 {0} 个项目。");
        phrases.put(Phrase.STATUS_DATABASE, "数据库：使用 {0}。");
        phrases.put(Phrase.STATUS_INTEGRATION, "{0}：集成已{启用|禁用}。");
        phrases.put(Phrase.STATUS_LICENSE, "许可证：{0}");
        phrases.put(Phrase.STATUS_SYSTEM, "系统：{0}");
        phrases.put(Phrase.STATUS_VERSION, "版本：{0}");
        phrases.put(Phrase.TELEPORTED, "已传送到 {0}。");
        phrases.put(Phrase.TELEPORTED_SAFETY, "已将你传送到安全位置。");
        phrases.put(Phrase.TELEPORT_PLAYERS, "传送命令只能由玩家使用。");
        phrases.put(Phrase.TIME_DAYS, "{0} 天");
        phrases.put(Phrase.TIME_HOURS, "{0} 小时");
        phrases.put(Phrase.TIME_MINUTES, "{0} 分钟");
        phrases.put(Phrase.TIME_MONTHS, "{0} 月");
        phrases.put(Phrase.TIME_SECONDS, "{0} 秒");
        phrases.put(Phrase.TIME_WEEKS, "{0} 周");
        phrases.put(Phrase.TIME_YEARS, "{0} 年");
        phrases.put(Phrase.UPDATE_ERROR, "检查更新时发生错误。");
        phrases.put(Phrase.UPDATE_HEADER, "{0} 更新");
        phrases.put(Phrase.UPDATE_NOTICE, "通知：{0} 现已可用。");
        phrases.put(Phrase.UPGRADE_IN_PROGRESS, "升级进行中，请稍后再试。");
        phrases.put(Phrase.USER_NOT_FOUND, "未找到用户 \"{0}\"。");
        phrases.put(Phrase.USER_OFFLINE, "用户 \"{0}\" 不在线。");
        phrases.put(Phrase.USING_MYSQL, "正在使用 MySQL 存储数据。");
        phrases.put(Phrase.USING_SQLITE, "正在使用 SQLite 存储数据。");
        phrases.put(Phrase.VALID_DONATION_KEY, "捐赠密钥有效。");
        phrases.put(Phrase.VERSION_NOTICE, "版本 {0} 现已可用。");
        phrases.put(Phrase.VERSION_INCOMPATIBLE, "{0} {1} 不受支持。");
        phrases.put(Phrase.VERSION_REQUIRED, "需要 {0} {1} 或更高版本。");
        phrases.put(Phrase.WORLD_NOT_FOUND, "未找到世界 \"{0}\"。");

        userPhrases.putAll(phrases);
        translatedPhrases.putAll(phrases);
    }

}
