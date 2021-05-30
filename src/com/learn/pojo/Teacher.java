package com.learn.pojo;

public class Teacher {
    private String tno;
    private String tname;
    private String tsex;
    private String tbirth;
    private String prof;
    private String depart;
    private Course course;


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTbirth() {
        return tbirth;
    }

    public void setTbirth(String tbirth) {
        this.tbirth = tbirth;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", tsex='" + tsex + '\'' +
                ", tbirth='" + tbirth + '\'' +
                ", prof='" + prof + '\'' +
                ", depart='" + depart + '\'' +
                ", course=" + course +
                '}';
    }
}
