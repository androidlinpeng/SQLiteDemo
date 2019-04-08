package msgcopy.com.sqldemo;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String name;
    private int age;
    private int sex;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserInfo(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.info = builder.info;
    }

    public static class Builder {
        public String name;
        public int age;
        public int sex;
        public String info;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder sex(int sex) {
            this.sex = sex;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }

    }

}
