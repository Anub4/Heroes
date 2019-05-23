package model;

public class heroes {
    String name;

    public heroes(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
