package com.lzu.securingmicroservicesdemo;

public class Test {

    public void main(String[] args) {
        new DeprecatedClass().DeprecatedMethod();
    }
    class DeprecatedClass {
        @Deprecated
        public void DeprecatedMethod() {

        }
    }
}
