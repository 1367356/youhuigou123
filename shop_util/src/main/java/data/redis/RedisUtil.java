package data.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

public class RedisUtil {
    private Jedis jedis;

    // before注解的方法，在运行test注解的方法之前会运行
    @Before
    public void setup() {
        // 连接redis服务器
        jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("Connection to server sucessfully");
        // 增加一个Key检测是否连接成功
        // jedis.set("foo", "bar");
        // String value = jedis.get("foo");
        // System.out.println("foo-->"+value);
        // 查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());//输出PONG则连接成功
        System.out.println("--------------------------------------------");
    }

    /**
     * jedis存储字符串
     */
    @Test
    public void testString() {
        // -----添加数据----------
        jedis.set("name", "xinxin");// 向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));// 执行结果：xinxin

        jedis.append("name", " is my lover"); // 拼接 。
        // 如果用redis命令输入key对应的值是不能有空格的哦
        System.out.println(jedis.get("name"));

        jedis.del("name"); // 删除某个键
        System.out.println(jedis.get("name"));
        // 设置多个键值对
        jedis.mset("name", "liuling", "age", "23", "qq", "476777XXX");
        jedis.incr("age"); // 进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-"
                + jedis.get("qq"));

        jedis.set("china", "这是个中文值");
        System.out.println(jedis.get("china"));
    }

    /**
     * jedis操作Map
     *
     * jedis.hmset("user",map);//该方法将java中的map在redis中存成了hash类型的数据对象
     */
    @Test
    public void testMap() {
        // -----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);// hmset方法存入后，该user对象在redis里面是一个hash类型的数据
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        // 删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * jedis操作List
     *
     * jedis.lpush("java framework", "spring")：方法创建了名为java
     * framework的List集合（如果不存在），并添加了一个元素 该方法保存的对象在redis里也为List类型的集合
     */
    @Test
    public void testList() {
        // 删除该list内容
        jedis.del("java framework");
        // 取出所有数据，jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println("内容：" + jedis.lrange("java framework", 0, -1));
        // 先向key java framework中存放三条数据，在key对应list的头部添加字符串元素
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");

        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        // 在key对应list的尾部部添加字符串元素
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    /**
     * jedis操作Set
     *
     * jedis.sadd("user1", "liuling")：创建一个set集合user1（如果不存在），并添加一个元素liuling
     * 该方法保存的对象在redis里也为Set集合
     */
    @Test
    public void testSet() {
        // 添加
        jedis.sadd("user1", "liuling");
        jedis.sadd("user1", "xinxin");
        jedis.sadd("user1", "ling");
        jedis.sadd("user1", "zhangxinxin");
        jedis.sadd("user1", "who");
        // 移除noname
        jedis.srem("user1", "who");
        System.out.println(jedis.smembers("user1"));// 获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));// 判断 who
        // 是否是user集合的元素
        System.out.println(jedis.srandmember("user1"));
        System.out.println(jedis.scard("user1"));// 返回集合的元素个数

    }

    /**
     * jedis对key值的操作
     */
    @Test
    public void testKey() {
        Set keys = jedis.keys("*");// 列出所有的key，查找特定的key如：redis.keys("foo")
        Iterator t1 = keys.iterator();
        while (t1.hasNext()) {
            Object obj1 = t1.next();
            System.out.println(obj1);
        }

        // DEL 移除给定的一个或多个key。如果key不存在，则忽略该命令。
        // redis.del("name");

        // TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)
        System.out.println(jedis.ttl("name"));

        // PERSIST key 移除给定key的生存时间。
        jedis.persist("name");

        // EXISTS 检查给定key是否存在。
        jedis.exists("name");

        // MOVE key db
        // 将当前数据库(默认为0)的key移动到给定的数据库db当中。如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定key，或者key不存在于当前数据库，那么MOVE没有任何效果。
        jedis.move("name", 1);// 将name这个key，移动到数据库1

        // RENAME key newkey
        // 将key改名为newkey。当key和newkey相同或者key不存在时，返回一个错误。当newkey已经存在时，RENAME命令将覆盖旧值。
        jedis.rename("name", "foonew");

        // TYPE key 返回key所储存的值的类型。
        System.out.println(jedis.type("name"));// none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表)

        // EXPIRE key seconds 为给定key设置生存时间。当key过期时，它会被自动删除。
        jedis.expire("name", 5);// 5秒过期
    }


    /**
     * 测试Redis连接池
     *
     * 测试这个方法的时候要把@Befor注释掉，不运行setup方法
     */
//    @Test
//    public void testJedisPool() {
//        jedis = JedisPoolUtil.getJedis();
//        jedis.set("kunle", "困了就去lol");
//        System.out.println(jedis.get("kunle"));
//        // jedis.quit();//关闭连接用方法quit
//        JedisPoolUtil.returnResource(jedis);
//        jedis.set("kunle2", "aa");
//        System.out.println(jedis.get("kunle2"));
//    }

}