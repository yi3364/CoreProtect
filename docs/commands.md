# 指令说明
---

你可以通过输入 `/co` 使用以下指令。
___

## 指令总览

| 指令 | 说明 |
| --- | --- |
| [/co help](#co-help) | 显示指令列表 |
| [/co inspect](#co-inspect) | 开关检查器 |
| [/co lookup](#co-lookup) | 查询方块数据 |
| [/co rollback](#co-rollback) | 回滚方块数据 |
| [/co restore](#co-restore) | 还原方块数据 |
| [/co purge](#co-purge) | 删除旧方块数据 |
| [/co reload](#co-reload) | 重载配置文件 |
| [/co status](#co-status) | 查看插件状态 |
| [/co consumer](#co-consumer) | 切换 consumer 队列处理 |

### 快捷指令

| 指令 | 说明 |
| --- | --- |
| /co near | 查询你周围半径5格内的更改 |
| /co undo | 通过相反操作撤销回滚/还原 |

---

## 指令详情

*详细指令信息如下。*

### /co help
在游戏内显示指令列表。
___

### /co inspect
启用检查器。再次输入该指令可关闭检查器。你也可以直接使用 `/co i`。
___

### /co lookup
执行查询。几乎所有参数都是可选的。

| 指令 | 参数 |
| --- | --- |
| /co lookup | `u:<用户> t:<时间> r:<半径> a:<操作> i:<包含> e:<排除>` |
| /co l | *`/co lookup <参数>`* |

#### 参数说明
| 参数 | 说明 |
| --- | --- |
| [`u:<user>`](#uuser) | 指定要查询的玩家 |
| [`t:<time>`](#ttime) | 指定要查询的时间范围 |
| [`r:<radius>`](#rradius) | 指定查询的半径范围 |
| [`a:<action>`](#aaction) | 限定查询的操作类型 |
| [`i:<include>`](#iinclude) | 查询时包含指定方块/实体 |
| [`e:<exclude>`](#eexclude) | 查询时排除指定方块/实体 |
| [`#<hashtag>`](#hashtag) | 添加标签以执行附加操作 |

#### 分页

如果返回多页结果，可使用 `/co lookup <页码>` 切换页面。  
要更改每页显示的行数，使用 `/co lookup <页码>:<行数>`。

> 例如，`/co l 1:10` 会返回第1页的10条数据。

---

### /co rollback
执行回滚。参数与 [查询](#参数说明) 相同。  
*回滚可用于撤销玩家的操作。*

| 指令 | 参数 |
| --- | --- |
| /co rollback | `u:<用户> t:<时间> r:<半径> a:<操作> i:<包含> e:<排除>` |
| /co rb | *`/co rollback <参数>`* |

---

### /co restore
执行还原。参数与 [查询](#参数说明) 相同。  
*还原可用于撤销回滚或恢复玩家操作。*

| 指令 | 参数 |
| --- | --- |
| /co restore | `u:<用户> t:<时间> r:<半径> a:<操作> i:<包含> e:<排除>` |
| /co rs | *`/co restore <参数>`* |

---

### /co purge
清理旧方块数据。如果你不需要旧数据，可以用来释放硬盘空间。

| 指令 | 参数 |
| --- | --- |
| /co purge | `t:<时间> r:<世界> i:<包含>` |

例如，`/co purge t:30d` 会删除所有30天前的数据，仅保留最近30天的数据。

> 在游戏内使用时，只能清理30天前的数据。  
> 控制台使用时，只能清理24小时前的数据。

**清理指定世界**  
CoreProtect v19+ 可选指定世界。  
如 `/co purge t:30d r:#world_nether` 只会删除地狱世界30天前的数据，不影响其他世界。

**清理指定方块**  
CoreProtect v23+ 可选指定方块类型。  
如 `/co purge t:30d i:stone,dirt` 只会删除30天前的石头和泥土数据，不影响其他方块。

**MySQL 优化**  
CoreProtect v2.15+，在指令末尾加 `#optimize`（如 `/co purge t:30d #optimize`）会同时优化表并回收磁盘空间。  
仅 MySQL 可用，SQLite 默认已自动优化。

*注意：加 #optimize 会显著降低清理速度，通常不需要使用。*

___

### /co reload
重载配置文件。
___

### /co status
显示插件状态和版本信息。
___

### /co consumer
控制台命令，用于暂停或恢复 consumer 队列处理。
___

## 参数详情

### `u:<user>`

*你可以指定一个或多个玩家。* 

* 例子：`u:Notch`
* 例子：`u:Notch,Intelli`
* 例子：`u:#fire,#tnt,#creeper,#explosion`

---

### `t:<time>`

*你可以指定周、天、小时、分钟和秒。*  
*时间单位可组合，也可用小数。*

* 例子：`t:2w,5d,7h,2m,10s`
* 例子：`t:5d2h`
* 例子：`t:1h-2h` *(1到2小时之间)*
* 例子：`t:2.50h` *(两个半小时)*

---

### `r:<radius>`

*数字半径，表示以你当前位置为中心的方块范围。*

* 例子：`r:10` *(以你为中心，半径10格内)*
* 例子：`r:#world_the_end` *(指定世界)*
* 例子：`r:#global` *(全服范围)*
* 例子：`r:#worldedit` 或 `r:#we` *(WorldEdit 选区)*

---

### `a:<action>`

*限制命令只针对特定操作*

* 例子：`a:+block` *(只包含放置的方块)*

#### 操作类型
| 操作 | 说明 |
| --- | --- |
| `a:block` | 方块放置/破坏 |
| `a:+block` | 方块放置 |
| `a:-block` | 方块破坏 |
| `a:chat` | 聊天消息 |
| `a:click` | 玩家交互 |
| `a:command` | 使用的指令 |
| `a:container` | 物品放入或取出箱子 |
| `a:+container` | 物品放入箱子 |
| `a:-container` | 物品取出箱子 |
| `a:inventory` | 玩家背包物品增减 |
| `a:+inventory` | 玩家背包物品增加 |
| `a:-inventory` | 玩家背包物品减少 |
| `a:item` | 玩家丢弃、投掷、拾取、存取、取出物品 |
| `a:+item` | 玩家拾取或取出物品 |
| `a:-item` | 玩家丢弃、投掷或存入物品 |
| `a:kill` | 实体/动物被击杀 |
| `a:session` | 玩家登录/退出 |
| `a:+session` | 玩家登录 |
| `a:-session` | 玩家退出 |
| `a:sign` | 告示牌内容书写 |
| `a:username` | 用户名更改 |

---

### `i:<include>`

*用于指定要包含的方块/物品/实体。* 

* 例子：`i:stone` *(只包含石头)*
* 例子：`i:stone,oak_wood,bedrock` *(指定多个方块)*

> 方块名称列表见 [https://coreprotect.net/wiki-blocks](https://coreprotect.net/wiki-blocks)。  
> 实体名称列表见 [https://coreprotect.net/wiki-entities](https://coreprotect.net/wiki-entities)。

---

### `e:<exclude>`

*用于排除某个方块/物品/实体/玩家。*

* 例子：`e:tnt` *(排除TNT)*

---

### `#<hashtag>`

在指令末尾添加标签以执行附加操作。

* 例子：`#preview` *(执行回滚预览)*

#### 标签列表
| 标签 | 作用 |
| --- | --- |
| `#preview` | 预览回滚/还原 |
| `#count` | 返回查询结果的行数 |
| `#verbose` | 回滚/还原时显示详细信息 |
| `#silent` | 回滚/还原时仅显示最简信息 |

___

## 示例命令

### 回滚命令示例

默认情况下，如果未指定半径，则会应用半径为10，仅回滚你周围10格内的操作。使用 `r:#global` 可进行全服回滚。

* `/co rollback Notch t:1h`  
  *(回滚 Notch 最近1小时内的操作（默认半径10格）)*
* `/co rollback u:Notch,Intelli t:1h #preview`  
  *(预览回滚 Notch 和 Intelli 最近1小时内的操作（默认半径10格）)*
* `/co rollback u:Notch t:23h17m`  
  *(回滚 Notch 最近23小时17分钟内的操作（默认半径10格）)*
* `/co rollback u:Notch t:1h i:stone`  
  *(仅回滚 Notch 最近1小时内放置/破坏的石头（默认半径10格）)*
* `/co rollback u:Notch t:1h i:stone a:-block`  
  *(仅回滚 Notch 最近1小时内破坏的石头（默认半径10格）)*
* `/co rollback u:Notch t:1h r:#global e:stone,dirt`  
  *(回滚 Notch 最近1小时内除石头和泥土外的所有操作)*
* `/co rollback u:Notch t:1h r:20`  
  *(回滚 Notch 最近1小时内距离你20格范围内的破坏)*
* `/co rollback u:Notch t:1h r:#nether`  
  *(仅回滚 Notch 最近1小时在地狱世界的破坏)*
* `/co rollback u:Notch t:5m a:inventory`  
  *(回滚 Notch 最近5分钟内的背包操作)*
* `/co rollback t:15m r:30`  
  *(回滚任意人在你周围30格内最近15分钟内的所有操作)*
* `/co rollback t:15m r:#worldedit`  
  *(回滚 WorldEdit 选区内最近15分钟内的所有操作)*

---

### 查询命令示例

查询命令与回滚命令基本相同。主要区别在于查询默认不限制半径，即所有查询默认全局搜索。

* `/co lookup i:diamond_ore t:1h a:-block`  
  *(查询最近1小时内被挖掘的所有钻石矿石)*
* `/co lookup u:Notch t:30m a:chat`  
  *(查询 Notch 最近30分钟内发送的聊天消息)*
* `/co lookup u:Notch t:3d a:inventory`  
  *(查询 Notch 最近3天内的背包操作)*
* `/co lookup u:Notch a:login`  
  *(查询 Notch 的所有登录记录)*
* `/co lookup u:Notch a:login`  
  *(查询 Notch 的所有登录记录)*
* `/co lookup u:Notch a:username`  
  *(查询 Notch 曾用过的用户名)*

___
