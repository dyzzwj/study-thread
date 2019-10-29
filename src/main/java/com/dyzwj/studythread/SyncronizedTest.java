package com.dyzwj.studythread;

import sun.security.provider.certpath.SunCertPathBuilder;

public class SyncronizedTest {


    public static void main(String[] args) {
        synchronized (SyncronizedTest.class){

        }
        method();

    }

    public synchronized static void method(){



    }
}
