package com.shiro.configBean;

import com.shiro.entity.mysql.MUserTable;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class passwordHelper {

    private static String algorithmName="md5";
    private static int hashIterations=1024;

    public static MUserTable encryptPassword(MUserTable mUserTable){
        System.out.println(mUserTable);
        ByteSource credentialsalt = ByteSource.Util.bytes(mUserTable.getUsername());

        String stringPassword=mUserTable.getPassword();

        Object password = new SimpleHash(algorithmName, stringPassword,
                credentialsalt, hashIterations).toHex();

        mUserTable.setPassword(String.valueOf(password));
        return mUserTable;
    }
}
