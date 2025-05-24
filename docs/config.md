# 配置说明

CoreProtect 的配置文件位于 CoreProtect 文件夹下的 `config.yml`。

## 单世界配置

如果你想为某个特定世界修改日志记录设置，请按以下步骤操作：

1. 将 config.yml 文件复制并重命名为该世界的名称（例如 world_nether.yml）
2. 在新文件中，根据需要修改日志记录设置。
3. 重启服务器，或在游戏内输入 "/co reload"。

二级配置文件会覆盖 config.yml 中的对应设置。如果你在二级配置文件中省略某个选项，则会使用 config.yml 中的设置。

#### 示例
* 如果你想禁用末地（The End）的所有日志记录，将 `config.yml` 复制为 `world_the_end.yml`（需与世界文件夹名一致），然后在新文件中禁用所有日志选项即可。
* 如果你只想禁用地狱（Nether）中的实体死亡日志，但保留其他日志设置，只需新建一个名为 `world_nether.yml` 的文件，内容为 "rollback-entities: false"。

## 禁用日志记录

如需为特定用户、方块或指令禁用日志记录，请按以下步骤操作：

1. 在 CoreProtect 插件目录下创建一个名为 `blacklist.txt` 的文件。
2. 在文件中输入你想禁用日志记录的用户名（或指令），每行一个。
3. 重启服务器，或在游戏内输入 "/co reload"。

此方法可用于禁用非玩家用户（如 "#creeper"）的日志记录。例如，如果你想禁用用户 "Notch"、TNT 爆炸、石头方块和 "/help" 指令的日志记录，blacklist.txt 文件内容如下：
```text
Notch
#tnt
/help
minecraft:stone
```

*请注意，禁用方块日志记录需要 CoreProtect v23+，并且必须包含命名空间。例如，禁用泥土方块日志时，需写为 "minecraft:dirt"。*