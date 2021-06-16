package com.example.demo.aop;

import lombok.Data;

/**
 * @author shisi
 * @date 2021/06/07 17:29
 **/
@Data
public class Demo {

    interface RoleOperation{
        String op();
    }

    enum RoleEnum implements RoleOperation{
        ROLE_ROOT_ADMIN{
            @Override
            public String op() {
                return "ROLE_ROOT_ADMIN================================";
            }
        },
        ROLE_ORDER_ADMIN{
            @Override
            public String op() {
                return "ROLE_ORDER_ADMIN================================";
            }
        },
        ROLE_NORMAL{
            @Override
            public String op() {
                return "ROLE_NORMAL================================";
            }
        };

    }

    static class JudgeRole {
        public String judge(String roleName) {
            return RoleEnum.valueOf(roleName).op();
        }
    }

    public static void main(String[] args) {
        JudgeRole role=new JudgeRole();
        System.out.println(RoleEnum.ROLE_ORDER_ADMIN.name());
        String judge = role.judge(RoleEnum.ROLE_ORDER_ADMIN.name());
        System.out.println(judge);

    }
}
