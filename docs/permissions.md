# 权限列表

以下权限可用于限制插件内的功能。

---

## 基础权限

* **coreprotect.\*** *(默认: op)*  
  允许访问所有 CoreProtect 指令。  
  &nbsp;
* **coreprotect.inspect** *(默认: op)*  
  允许使用 CoreProtect 检查器指令。  
  &nbsp;
* **coreprotect.lookup** *(默认: op)*  
  允许使用 CoreProtect 查询指令。  
  &nbsp;
* **coreprotect.rollback** *(默认: op)*  
  允许使用 CoreProtect 回滚指令。  
  &nbsp;
* **coreprotect.restore** *(默认: op)*  
  允许使用 CoreProtect 还原指令。  
  &nbsp;
* **coreprotect.teleport** *(默认: op)*  
  允许使用 CoreProtect 传送指令。  
  &nbsp;
* **coreprotect.help** *(默认: op)*  
  允许使用 CoreProtect 帮助指令。  
  &nbsp;
* **coreprotect.purge** *(默认: op)*  
  允许使用 CoreProtect 清理指令。  
  &nbsp;
* **coreprotect.reload** *(默认: op)*  
  允许使用 CoreProtect 重载指令。  
  &nbsp;
* **coreprotect.status** *(默认: op)*  
  允许使用 CoreProtect 状态指令。  
  &nbsp;
* **coreprotect.consumer** *(默认: op)*  
  允许使用 CoreProtect consumer 指令。  
  &nbsp;
* **coreprotect.networking** *(默认: op)*  
  允许使用 CoreProtect 网络 API。  
  
---

## 子权限

* **coreprotect.lookup.block** *(默认: op)*  
  可作为负权限使用以阻止方块查询。  
  &nbsp;
* **coreprotect.lookup.chat** *(默认: op)*  
  可作为负权限使用以阻止聊天查询。  
  &nbsp;
* **coreprotect.lookup.click** *(默认: op)*  
  可作为负权限使用以阻止交互查询。  
  &nbsp;
* **coreprotect.lookup.command** *(默认: op)*  
  可作为负权限使用以阻止指令查询。  
  &nbsp;
* **coreprotect.lookup.container** *(默认: op)*  
  可作为负权限使用以阻止容器查询。  
  &nbsp;
* **coreprotect.lookup.inventory** *(默认: op)*  
  可作为负权限使用以阻止背包查询。  
  &nbsp;
* **coreprotect.lookup.item** *(默认: op)*  
  可作为负权限使用以阻止物品查询。  
  &nbsp;
* **coreprotect.lookup.kill** *(默认: op)*  
  可作为负权限使用以阻止实体击杀查询。  
  &nbsp;
* **coreprotect.lookup.near** *(默认: op)*  
  可作为负权限使用以阻止 near 指令查询。  
  &nbsp;
* **coreprotect.lookup.session** *(默认: op)*  
  可作为负权限使用以阻止会话查询。  
  &nbsp;
* **coreprotect.lookup.sign** *(默认: op)*  
  可作为负权限使用以阻止告示牌查询。  
  &nbsp;
* **coreprotect.lookup.username** *(默认: op)*  
  可作为负权限使用以阻止用户名更改查询。  

---

## 指令权限

* **coreprotect.co** *(默认: true)*  
  允许使用 CoreProtect 的 "/co" 指令。  
  &nbsp;
* **coreprotect.core** *(默认: false)*  
  允许使用 CoreProtect 的 "/core" 指令。  
  &nbsp;
* **coreprotect.coreprotect** *(默认: false)*  
  允许使用 CoreProtect 的 "/coreprotect" 指令。