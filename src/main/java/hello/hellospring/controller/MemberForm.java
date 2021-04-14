package hello.hellospring.controller;

public class MemberForm {
    private String name;    /*** createMemberForm의 name="name"이 name변수로 들어옴 ***/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
