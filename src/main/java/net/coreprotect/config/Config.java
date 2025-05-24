package net.coreprotect.config;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.World;

import net.coreprotect.CoreProtect;
import net.coreprotect.language.Language;
import net.coreprotect.thread.Scheduler;

public class Config extends Language {

    private static final Map<String, String[]> HEADERS = new HashMap<>();
    private static final Map<String, String> DEFAULT_VALUES = new LinkedHashMap<>();
    private static final Map<String, Config> CONFIG_BY_WORLD_NAME = new HashMap<>();
    private static final String DEFAULT_FILE_HEADER = "# CoreProtect Config";
    public static final String LINE_SEPARATOR = "\n";

    private static final Config GLOBAL = new Config();
    private final HashMap<String, String> config;
    private Config defaults;

    public String DONATION_KEY;
    public String PREFIX;
    public String MYSQL_HOST;
    public String MYSQL_DATABASE;
    public String MYSQL_USERNAME;
    public String MYSQL_PASSWORD;
    public String LANGUAGE;
    public boolean ENABLE_SSL;
    public boolean DISABLE_WAL;
    public boolean HOVER_EVENTS;
    public boolean DATABASE_LOCK;
    public boolean LOG_CANCELLED_CHAT;
    public boolean HOPPER_FILTER_META;
    public boolean EXCLUDE_TNT;
    public boolean NETWORK_DEBUG;
    public boolean MYSQL;
    public boolean CHECK_UPDATES;
    public boolean API_ENABLED;
    public boolean VERBOSE;
    public boolean ROLLBACK_ITEMS;
    public boolean ROLLBACK_ENTITIES;
    public boolean SKIP_GENERIC_DATA;
    public boolean BLOCK_PLACE;
    public boolean BLOCK_BREAK;
    public boolean NATURAL_BREAK;
    public boolean BLOCK_MOVEMENT;
    public boolean PISTONS;
    public boolean BLOCK_BURN;
    public boolean BLOCK_IGNITE;
    public boolean EXPLOSIONS;
    public boolean ENTITY_CHANGE;
    public boolean ENTITY_KILLS;
    public boolean SIGN_TEXT;
    public boolean BUCKETS;
    public boolean LEAF_DECAY;
    public boolean TREE_GROWTH;
    public boolean MUSHROOM_GROWTH;
    public boolean VINE_GROWTH;
    public boolean SCULK_SPREAD;
    public boolean PORTALS;
    public boolean WATER_FLOW;
    public boolean LAVA_FLOW;
    public boolean LIQUID_TRACKING;
    public boolean ITEM_TRANSACTIONS;
    public boolean ITEM_DROPS;
    public boolean ITEM_PICKUPS;
    public boolean HOPPER_TRANSACTIONS;
    public boolean PLAYER_INTERACTIONS;
    public boolean PLAYER_MESSAGES;
    public boolean PLAYER_COMMANDS;
    public boolean PLAYER_SESSIONS;
    public boolean UNKNOWN_LOGGING;
    public boolean USERNAME_CHANGES;
    public boolean WORLDEDIT;
    public int MAXIMUM_POOL_SIZE;
    public int MYSQL_PORT;
    public int DEFAULT_RADIUS;
    public int MAX_RADIUS;

    static {
        DEFAULT_VALUES.put("donation-key", "");
        DEFAULT_VALUES.put("use-mysql", "false");
        DEFAULT_VALUES.put("table-prefix", "co_");
        DEFAULT_VALUES.put("mysql-host", "127.0.0.1");
        DEFAULT_VALUES.put("mysql-port", "3306");
        DEFAULT_VALUES.put("mysql-database", "database");
        DEFAULT_VALUES.put("mysql-username", "root");
        DEFAULT_VALUES.put("mysql-password", "");
        DEFAULT_VALUES.put("language", "zh");
        DEFAULT_VALUES.put("check-updates", "true");
        DEFAULT_VALUES.put("api-enabled", "true");
        DEFAULT_VALUES.put("verbose", "true");
        DEFAULT_VALUES.put("default-radius", "10");
        DEFAULT_VALUES.put("max-radius", "100");
        DEFAULT_VALUES.put("rollback-items", "true");
        DEFAULT_VALUES.put("rollback-entities", "true");
        DEFAULT_VALUES.put("skip-generic-data", "true");
        DEFAULT_VALUES.put("block-place", "true");
        DEFAULT_VALUES.put("block-break", "true");
        DEFAULT_VALUES.put("natural-break", "true");
        DEFAULT_VALUES.put("block-movement", "true");
        DEFAULT_VALUES.put("pistons", "true");
        DEFAULT_VALUES.put("block-burn", "true");
        DEFAULT_VALUES.put("block-ignite", "true");
        DEFAULT_VALUES.put("explosions", "true");
        DEFAULT_VALUES.put("entity-change", "true");
        DEFAULT_VALUES.put("entity-kills", "true");
        DEFAULT_VALUES.put("sign-text", "true");
        DEFAULT_VALUES.put("buckets", "true");
        DEFAULT_VALUES.put("leaf-decay", "true");
        DEFAULT_VALUES.put("tree-growth", "true");
        DEFAULT_VALUES.put("mushroom-growth", "true");
        DEFAULT_VALUES.put("vine-growth", "true");
        DEFAULT_VALUES.put("sculk-spread", "true");
        DEFAULT_VALUES.put("portals", "true");
        DEFAULT_VALUES.put("water-flow", "true");
        DEFAULT_VALUES.put("lava-flow", "true");
        DEFAULT_VALUES.put("liquid-tracking", "true");
        DEFAULT_VALUES.put("item-transactions", "true");
        DEFAULT_VALUES.put("item-drops", "true");
        DEFAULT_VALUES.put("item-pickups", "true");
        DEFAULT_VALUES.put("hopper-transactions", "true");
        DEFAULT_VALUES.put("player-interactions", "true");
        DEFAULT_VALUES.put("player-messages", "true");
        DEFAULT_VALUES.put("player-commands", "true");
        DEFAULT_VALUES.put("player-sessions", "true");
        DEFAULT_VALUES.put("username-changes", "true");
        DEFAULT_VALUES.put("worldedit", "true");

        HEADERS.put("donation-key", new String[] { "# CoreProtect 是捐赠软件。捐赠密钥请访问 coreprotect.net/donate/ 获取。" });
        HEADERS.put("use-mysql", new String[] { "# MySQL 为可选项，并非必需。", "# 如需使用 MySQL，请启用下方选项并填写相关信息。" });
        HEADERS.put("language", new String[] { "# 修改后将自动尝试翻译语言短语。", "# 语言代码列表：https://coreprotect.net/languages/" });
        HEADERS.put("check-updates", new String[] { "# 启用后，CoreProtect 会在服务器启动时检查更新。", "# 如有新版本，将在服务器控制台通知你。" });
        HEADERS.put("api-enabled", new String[] { "# 启用后，其他插件可调用 CoreProtect API。" });
        HEADERS.put("verbose", new String[] { "# 启用后，回滚和还原时会显示更多详细信息。", "# 也可通过在回滚命令后添加 \"#verbose\" 手动触发。" });
        HEADERS.put("default-radius", new String[] { "# 未指定半径时，回滚或还原将默认使用此值。设为 \"0\" 可禁用自动半径。" });
        HEADERS.put("max-radius", new String[] { "# 指令可用的最大半径。设为 \"0\" 可禁用。", "# 如需不限制半径，可用 \"r:#global\"。" });
        HEADERS.put("rollback-items", new String[] { "# 启用后，回滚时会包含从容器等取出的物品。" });
        HEADERS.put("rollback-entities", new String[] { "# 启用后，回滚时会包含实体（如被击杀的动物）。" });
        HEADERS.put("skip-generic-data", new String[] { "# 启用后，不记录通用数据，如僵尸在白天燃烧。"});
        HEADERS.put("block-place", new String[] { "# 记录玩家放置的方块。" });
        HEADERS.put("block-break", new String[] { "# 记录玩家破坏的方块。" });
        HEADERS.put("natural-break", new String[] { "# 记录因其他方块破坏而掉落的方块，如玩家破坏泥土导致火把掉落。", "# 该项对床/门等正确回滚是必须的。" });
        HEADERS.put("block-movement", new String[] { "# 正确追踪方块移动，如沙子或沙砾下落。" });
        HEADERS.put("pistons", new String[] { "# 正确追踪活塞推动的方块。" });
        HEADERS.put("block-burn", new String[] { "# 记录被火烧毁的方块。" });
        HEADERS.put("block-ignite", new String[] { "# 记录方块自然点燃，如火焰蔓延。" });
        HEADERS.put("explosions", new String[] { "# 记录爆炸，如 TNT 和苦力怕。" });
        HEADERS.put("entity-change", new String[] { "# 记录实体改变方块，如末影人破坏方块。" });
        HEADERS.put("entity-kills", new String[] { "# 记录被击杀的实体，如牛和末影人。" });
        HEADERS.put("sign-text", new String[] { "# 记录告示牌内容。禁用后回滚时告示牌内容会为空。" });
        HEADERS.put("buckets", new String[] { "# 记录玩家用桶放置/移除的水和岩浆源。" });
        HEADERS.put("leaf-decay", new String[] { "# 记录树叶自然腐烂。" });
        HEADERS.put("tree-growth", new String[] { "# 记录树木生长。树木会关联到种植树苗的玩家。" });
        HEADERS.put("mushroom-growth", new String[] { "# 记录蘑菇生长。" });
        HEADERS.put("vine-growth", new String[] { "# 记录藤蔓自然生长。" });
        HEADERS.put("sculk-spread", new String[] { "# 记录幽匿块由幽匿催发体扩散。" });
        HEADERS.put("portals", new String[] { "# 记录地狱门等传送门自然生成。" });
        HEADERS.put("water-flow", new String[] { "# 记录水流动。如水流破坏火把等方块，可正确回滚。" });
        HEADERS.put("lava-flow", new String[] { "# 记录岩浆流动。如岩浆流破坏火把等方块，可正确回滚。" });
        HEADERS.put("liquid-tracking", new String[] { "# 正确追踪液体流动并关联到玩家。", "# 例如，玩家放水导致水流破坏火把，可通过回滚该玩家全部恢复。" });
        HEADERS.put("item-transactions", new String[] { "# 追踪物品操作，如玩家从箱子、熔炉、发射器取出物品。" });
        HEADERS.put("item-drops", new String[] { "# 记录玩家丢弃的物品。" });
        HEADERS.put("item-pickups", new String[] { "# 记录玩家捡起的物品。" });
        HEADERS.put("hopper-transactions", new String[] { "# 追踪所有漏斗操作，如漏斗从箱子、熔炉、发射器取出物品。" });
        HEADERS.put("player-interactions", new String[] { "# 追踪玩家交互，如开门、按按钮、打开箱子。交互无法回滚。" });
        HEADERS.put("player-messages", new String[] { "# 记录玩家发送的聊天消息。" });
        HEADERS.put("player-commands", new String[] { "# 记录玩家使用的所有指令。" });
        HEADERS.put("player-sessions", new String[] { "# 记录玩家的登录与退出。" });
        HEADERS.put("username-changes", new String[] { "# 记录玩家更改 Minecraft 用户名。" });
        HEADERS.put("worldedit", new String[] { "# 如服务器安装了 WorldEdit 插件，记录其造成的更改。" });
    }

    private void readValues() {
        this.ENABLE_SSL = this.getBoolean("enable-ssl", false);
        this.DISABLE_WAL = this.getBoolean("disable-wal", false);
        this.HOVER_EVENTS = this.getBoolean("hover-events", true);
        this.DATABASE_LOCK = this.getBoolean("database-lock", true);
        this.LOG_CANCELLED_CHAT = this.getBoolean("log-cancelled-chat", true);
        this.HOPPER_FILTER_META = this.getBoolean("hopper-filter-meta", false);
        this.EXCLUDE_TNT = this.getBoolean("exclude-tnt", false);
        this.NETWORK_DEBUG = this.getBoolean("network-debug", false);
        this.UNKNOWN_LOGGING = this.getBoolean("unknown-logging", false);
        this.MAXIMUM_POOL_SIZE = this.getInt("maximum-pool-size", 10);
        this.DONATION_KEY = this.getString("donation-key");
        this.MYSQL = this.getBoolean("use-mysql");
        this.PREFIX = this.getString("table-prefix");
        this.MYSQL_HOST = this.getString("mysql-host");
        this.MYSQL_PORT = this.getInt("mysql-port");
        this.MYSQL_DATABASE = this.getString("mysql-database");
        this.MYSQL_USERNAME = this.getString("mysql-username");
        this.MYSQL_PASSWORD = this.getString("mysql-password");
        this.LANGUAGE = this.getString("language");
        this.CHECK_UPDATES = this.getBoolean("check-updates");
        this.API_ENABLED = this.getBoolean("api-enabled");
        this.VERBOSE = this.getBoolean("verbose");
        this.DEFAULT_RADIUS = this.getInt("default-radius");
        this.MAX_RADIUS = this.getInt("max-radius");
        this.ROLLBACK_ITEMS = this.getBoolean("rollback-items");
        this.ROLLBACK_ENTITIES = this.getBoolean("rollback-entities");
        this.SKIP_GENERIC_DATA = this.getBoolean("skip-generic-data");
        this.BLOCK_PLACE = this.getBoolean("block-place");
        this.BLOCK_BREAK = this.getBoolean("block-break");
        this.NATURAL_BREAK = this.getBoolean("natural-break");
        this.BLOCK_MOVEMENT = this.getBoolean("block-movement");
        this.PISTONS = this.getBoolean("pistons");
        this.BLOCK_BURN = this.getBoolean("block-burn");
        this.BLOCK_IGNITE = this.getBoolean("block-ignite");
        this.EXPLOSIONS = this.getBoolean("explosions");
        this.ENTITY_CHANGE = this.getBoolean("entity-change");
        this.ENTITY_KILLS = this.getBoolean("entity-kills");
        this.SIGN_TEXT = this.getBoolean("sign-text");
        this.BUCKETS = this.getBoolean("buckets");
        this.LEAF_DECAY = this.getBoolean("leaf-decay");
        this.TREE_GROWTH = this.getBoolean("tree-growth");
        this.MUSHROOM_GROWTH = this.getBoolean("mushroom-growth");
        this.VINE_GROWTH = this.getBoolean("vine-growth");
        this.SCULK_SPREAD = this.getBoolean("sculk-spread");
        this.PORTALS = this.getBoolean("portals");
        this.WATER_FLOW = this.getBoolean("water-flow");
        this.LAVA_FLOW = this.getBoolean("lava-flow");
        this.LIQUID_TRACKING = this.getBoolean("liquid-tracking");
        this.ITEM_TRANSACTIONS = this.getBoolean("item-transactions");
        this.ITEM_DROPS = this.getBoolean("item-drops");
        this.ITEM_PICKUPS = this.getBoolean("item-pickups");
        this.HOPPER_TRANSACTIONS = this.getBoolean("hopper-transactions");
        this.PLAYER_INTERACTIONS = this.getBoolean("player-interactions");
        this.PLAYER_MESSAGES = this.getBoolean("player-messages");
        this.PLAYER_COMMANDS = this.getBoolean("player-commands");
        this.PLAYER_SESSIONS = this.getBoolean("player-sessions");
        this.USERNAME_CHANGES = this.getBoolean("username-changes");
        this.WORLDEDIT = this.getBoolean("worldedit");
    }

    public static void init() throws IOException {
        parseConfig(loadFiles(ConfigFile.CONFIG));
        // pass variables to ConfigFile.parseConfig(ConfigFile.loadFiles());
    }

    public static Config getGlobal() {
        return GLOBAL;
    }

    // returns a world specific config if it exists, otherwise the global config
    public static Config getConfig(final World world) {
        return getConfig(world.getName());
    }

    public static Config getConfig(final String worldName) {
        Config ret = CONFIG_BY_WORLD_NAME.get(worldName);
        if (ret == null) {
            ret = CONFIG_BY_WORLD_NAME.getOrDefault(worldName, GLOBAL);
            CONFIG_BY_WORLD_NAME.put(worldName, ret);
        }
        return ret;
    }

    public Config() {
        this.config = new LinkedHashMap<>();
    }

    public void setDefaults(final Config defaults) {
        this.defaults = defaults;
    }

    private String get(final String key, final String dfl) {
        String configured = this.config.get(key);
        if (configured == null) {
            if (dfl != null) {
                return dfl;
            }
            if (this.defaults == null) {
                configured = DEFAULT_VALUES.get(key);
            }
            else {
                configured = this.defaults.config.getOrDefault(key, DEFAULT_VALUES.get(key));
            }
        }
        return configured;
    }

    private boolean getBoolean(final String key) {
        final String configured = this.get(key, null);
        return configured != null && configured.startsWith("t");
    }

    private boolean getBoolean(final String key, final boolean dfl) {
        final String configured = this.get(key, null);
        return configured == null ? dfl : configured.startsWith("t");
    }

    private int getInt(final String key) {
        return this.getInt(key, 0);
    }

    private int getInt(final String key, final int dfl) {
        String configured = this.get(key, null);

        if (configured == null) {
            return dfl;
        }

        configured = configured.replaceAll("[^0-9]", "");

        return configured.isEmpty() ? dfl : Integer.parseInt(configured);
    }

    private String getString(final String key) {
        final String configured = this.get(key, null);
        return configured == null ? "" : configured;
    }

    public void clearConfig() {
        this.config.clear();
    }

    public void loadDefaults() {
        this.clearConfig();
        this.readValues();
    }

    public void load(final InputStream in) throws IOException {
        // if we fail reading, we will not corrupt our current config.
        final Map<String, String> newConfig = new LinkedHashMap<>(this.config.size());
        ConfigFile.load(in, newConfig, false);

        this.clearConfig();
        this.config.putAll(newConfig);

        this.readValues();
    }

    private static Map<String, byte[]> loadFiles(String fileName) throws IOException {
        final CoreProtect plugin = CoreProtect.getInstance();
        final File configFolder = plugin.getDataFolder();
        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        final Map<String, byte[]> map = new HashMap<>();
        final File globalFile = new File(configFolder, fileName);

        if (globalFile.exists()) {
            // we always add options to the global config
            final byte[] data = Files.readAllBytes(globalFile.toPath());
            map.put("config", data);

            // can't modify GLOBAL, we're likely off-main here
            final Config temp = new Config();
            temp.load(new ByteArrayInputStream(data));
            temp.addMissingOptions(globalFile);
        }
        else {
            final Config temp = new Config();
            temp.loadDefaults();
            temp.addMissingOptions(globalFile);
        }

        for (final File worldConfigFile : configFolder.listFiles((File file) -> file.getName().endsWith(".yml"))) {
            final String name = worldConfigFile.getName();
            if (name.equals(ConfigFile.CONFIG) || name.equals(ConfigFile.LANGUAGE)) {
                continue;
            }

            map.put(name.substring(0, name.length() - ".yml".length()), Files.readAllBytes(worldConfigFile.toPath()));
        }

        return map;
    }

    // this should only be called on the main thread
    private static void parseConfig(final Map<String, byte[]> data) {
        if (!Bukkit.isPrimaryThread()) {
            // we call reloads asynchronously
            // for now this solution is good enough to ensure we only modify on the main thread
            final CompletableFuture<Void> complete = new CompletableFuture<>();

            Scheduler.runTask(CoreProtect.getInstance(), () -> {
                try {
                    parseConfig(data);
                }
                catch (final Throwable thr) {
                    if (thr instanceof ThreadDeath) {
                        throw (ThreadDeath) thr;
                    }
                    complete.completeExceptionally(thr);
                    return;
                }
                complete.complete(null);
            });

            complete.join();
            return;
        }

        CONFIG_BY_WORLD_NAME.clear();

        // we need to load global first since it is used for config defaults
        final byte[] defaultData = data.get("config");
        if (defaultData != null) {
            try {
                GLOBAL.load(new ByteArrayInputStream(defaultData));
            }
            catch (final IOException ex) {
                throw new RuntimeException(ex); // shouldn't happen
            }
        }
        else {
            GLOBAL.loadDefaults();
        }

        for (final Map.Entry<String, byte[]> entry : data.entrySet()) {
            final String worldName = entry.getKey();
            if (worldName.equals("config")) {
                continue;
            }

            final byte[] fileData = entry.getValue();
            final Config config = new Config();
            config.setDefaults(GLOBAL);

            try {
                config.load(new ByteArrayInputStream(fileData));
            }
            catch (final IOException ex) {
                throw new RuntimeException(ex); // shouldn't happen
            }

            CONFIG_BY_WORLD_NAME.put(worldName, config);
        }
    }

    public void addMissingOptions(final File file) throws IOException {
        final boolean writeHeader = !file.exists() || file.length() == 0;
        try (final FileOutputStream fout = new FileOutputStream(file, true)) {
            OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(fout), StandardCharsets.UTF_8);
            if (writeHeader) {
                out.append(DEFAULT_FILE_HEADER);
                out.append(LINE_SEPARATOR);
            }

            for (final Map.Entry<String, String> entry : DEFAULT_VALUES.entrySet()) {
                final String key = entry.getKey();
                final String defaultValue = entry.getValue();

                final String configuredValue = this.config.get(key);

                if (configuredValue != null) {
                    continue;
                }

                final String[] header = HEADERS.get(key);

                if (header != null) {
                    out.append(LINE_SEPARATOR);
                    for (final String headerLine : header) {
                        out.append(headerLine);
                        out.append(LINE_SEPARATOR);
                    }
                }

                out.append(key);
                out.append(": ");
                out.append(defaultValue);
                out.append(LINE_SEPARATOR);
            }

            out.close();
        }
    }
}
