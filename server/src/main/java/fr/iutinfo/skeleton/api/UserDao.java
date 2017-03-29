package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table users (id integer primary key autoincrement, name varchar(100), alias varchar(100), email varchar(100), phone varchar(10), passwdHash varchar(64), salt varchar(64))")
    void createUserTable();

    @SqlUpdate("insert into users (name, alias, email, phone, passwdHash, salt) values (:name, :alias, :email, :phone, :passwdHash, :salt)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlQuery("select * from users where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("id") String id);


    @SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByName(@Bind("name") String name);
    
    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);

    void close();
}
