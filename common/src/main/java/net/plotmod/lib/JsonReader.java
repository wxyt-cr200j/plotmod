package net.plotmod.lib;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    private static final Gson GSON = new Gson();

    /**
     * 深度合并多个 JsonObject
     */
    public static void deepMerge(JsonObject result, JsonObject... objects) {
        for (JsonObject obj : objects) {
            for (String key : obj.keySet()) {
                JsonElement value = obj.get(key);

                if (result.has(key)) {
                    JsonElement existing = result.get(key);

                    // 如果两个都是 JsonObject，递归合并
                    if (existing.isJsonObject() && value.isJsonObject()) {
                        deepMerge(existing.getAsJsonObject(), value.getAsJsonObject());
                    }
                    // 如果两个都是 JsonArray，拼接
                    else if (existing.isJsonArray() && value.isJsonArray()) {
                        JsonArray mergedArray = new JsonArray();
                        existing.getAsJsonArray().forEach(mergedArray::add);
                        value.getAsJsonArray().forEach(mergedArray::add);
                        result.add(key, mergedArray);
                    }
                    // 类型不同或其他情况，覆盖
                    else {
                        result.add(key, value);
                    }
                } else {
                    result.add(key, value);
                }
            }
        }
    }

    /**
     * 从 ResourceManager 中读取并合并 JSON
     */
    public static JsonObject readJson(ResourceLocation rl) {
        JsonObject jsonObject = new JsonObject();
        try {
            Minecraft client = Minecraft.getInstance();
            ResourceManager rm = client.getResourceManager();

            List<Resource> res = rm.getResourceStack(rl);
            for (Resource re : res) {
                try (InputStream is = re.open();
                     InputStreamReader reader = new InputStreamReader(is)) {
                    JsonElement parsed = GsonHelper.parse(reader);
                    if (parsed.isJsonObject()) {
                        deepMerge(jsonObject, parsed.getAsJsonObject());
                    }
                } catch (IOException e) {
                    e.printStackTrace(); // 打印异常方便调试
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GSON.fromJson("{}", JsonObject.class);
        }
        return jsonObject;
    }
}
