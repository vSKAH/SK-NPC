package fr.skah.sknpc.api.model;

/*
 *  * @Created on 2022 - 13:36
 *  * @Project SK-NPC
 *  * @Author Jimmy  / vSKAH#0075
 */

public class NpcSkinTexture {

    private final String textureBase64, textureSignature;

    public NpcSkinTexture(String textureBase64, String textureSignature) {
        this.textureBase64 = textureBase64;
        this.textureSignature = textureSignature;
    }

    public String getTextureBase64() {
        return textureBase64;
    }

    public String getTextureSignature() {
        return textureSignature;
    }

    @Override
    public String toString() {
        return "NpcSkinTexture{" +
                "textureBase64='" + textureBase64 + '\'' +
                ", textureSignature='" + textureSignature + '\'' +
                '}';
    }
}
