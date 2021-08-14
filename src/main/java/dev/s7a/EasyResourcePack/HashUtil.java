package dev.s7a.EasyResourcePack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ハッシュのユーティリティクラス
 */
public class HashUtil {
    /**
     * SHA-1 アルゴリズムの名前
     *
     * @see MessageDigest
     */
    private final static String AlgorithmName = "SHA-1";

    /**
     * SHA-1 を計算する
     *
     * @param filePath ファイルのパス
     * @return SHA-1
     */
    public static byte @Nullable [] sha1(String filePath) {
        Path path = Paths.get(filePath);
        byte @Nullable [] hash;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(AlgorithmName);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        try (DigestInputStream inputStream = new DigestInputStream(new BufferedInputStream(Files.newInputStream(path)), messageDigest)) {
            //noinspection StatementWithEmptyBody
            while (inputStream.read() != -1) ;
            hash = messageDigest.digest();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return hash;
    }

    /**
     * バイト配列を文字列に変換
     *
     * @param bytes バイト配列
     * @return 文字列
     */
    public static @NotNull String bytesToString(byte @Nullable [] bytes) {
        if (bytes != null) {
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                String hex = String.format("%02x", b);
                builder.append(hex);
            }
            return builder.toString();
        } else {
            return "";
        }
    }

    /**
     * 文字列をバイト配列に変換
     *
     * @param text 文字列
     * @return バイト配列
     */
    public static byte @Nullable [] stringToBytes(@Nullable String text) {
        if (text != null) {
            int len = text.length();
            byte[] bytes = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                bytes[i / 2] = (byte) ((Character.digit(text.charAt(i), 16) << 4) + Character.digit(text.charAt(i + 1), 16));
            }
            return bytes;
        } else {
            return null;
        }
    }
}
