# 网络 API

CoreProtect 网络 API 允许客户端通过数据包接收数据。

| 网络相关信息         |        |
|---------------------|--------|
| **网络协议版本：**   | 1      |
| **插件版本：**       | v21.3+ |

---

## 数据包

服务器仅在玩家拥有 `coreprotect.networking` 权限时才会响应。

---

## 服务器 → 客户端

### 数据包（Data Packet）
从数据库发送数据。

* 通道：`coreprotect:data`

| 类型: `Int` | 1                      | 2                      | 3                  | 4                  |
|-------------|------------------------|------------------------|--------------------|--------------------|
|             | 时间: `long`           | 时间: `long`           | 时间: `long`       | 时间: `long`       |
|             | 短语选择器: `UTF`      | 短语选择器: `UTF`      | 结果用户: `UTF`    | 结果用户: `UTF`    |
|             | 结果用户: `UTF`        | 结果用户: `UTF`        | 消息: `UTF`        | 目标: `UTF`        |
|             | 目标: `UTF`            | 数量: `Int`            | 告示牌: `Boolean`  |                    |
|             | 数量: `Int`            | X: `Int`               | X: `Int`           |                    |
|             | X: `Int`               | Y: `Int`               | Y: `Int`           |                    |
|             | Y: `Int`               | Z: `Int`               | Z: `Int`           |                    |
|             | Z: `Int`               | 世界名: `UTF`          | 世界名: `UTF`      |                    |
|             | 世界名: `UTF`          |                        |                    |                    |
|             | 已回滚: `Boolean`      |                        |                    |                    |
|             | 是否容器: `Boolean`    |                        |                    |                    |
|             | 已添加: `Boolean`      |                        |                    |                    |

示例（Fabric）：
```
ByteArrayInputStream in = new ByteArrayInputStream(buf.getWrittenBytes());
DataInputStream dis = new DataInputStream(in);
int type = dis.readInt();
long time = dis.readLong();
String selector = dis.readUTF();
String  resultUser = dis.readUTF();
String target = dis.readUTF();
int amount = dis.readInt();
int x = dis.readInt();
int y = dis.readInt();
int z = dis.readInt();
String worldName = dis.readUTF();
boolean rolledback = dis.readBoolean();
boolean isContainer = dis.readBoolean();
boolean added = dis.readBoolean();
```

### 握手包（Handshake Packet）
如果玩家已注册，则发送握手包。

* 通道：`coreprotect:handshake`
* 已注册: `Boolean`

---

## 客户端 → 服务器

### 握手包（Handshake Packet）
发送握手以注册

* 通道：`coreprotect:handshake`  
* Mod 版本: `UTF`  
* Mod Id: `UTF`   
* CoreProtect 协议: `Int`

示例（Fabric）：
```
PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
ByteArrayOutputStream msgBytes = new ByteArrayOutputStream();
DataOutputStream msgOut = new DataOutputStream(msgBytes);
msgOut.writeUTF(modVersion);
msgOut.writeUTF(modId);
msgOut.writeInt(coreprotectProtocol);
packetByteBuf.writeBytes(msgBytes.toByteArray());
```

---

## 调试

### /co network-debug
如果你已注册且拥有正确权限，可用于调试网络 API。  
要使用此指令，需在 CoreProtect `config.yml` 中设置 `network-debug: true`。

**示例**  
`/co network-debug <类型>`

___