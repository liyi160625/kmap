package org.andy.kmap.model.entity;

public class Detail {

    private int id;
    private String name;
    private String info;

    private Course course;


    public Detail(String name) {

        this.name = name;
    }


    public Detail(int id, String name) {

        this.id = id;
        this.name = name;
    }


    public int getId() {

        return this.id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getInfo() {

        return this.info;
    }

    public void setInfo(String info) {

        this.info = info;
    }

    public Course getCourse() {

        return this.course;
    }

    public void setCourse(Course course) {

        this.course = course;
    }
}