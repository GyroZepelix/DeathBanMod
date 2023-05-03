package hr.gyro.deathban;

import com.mojang.authlib.GameProfile;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.random.RandomGenerator;


public class ModEvent {

    private static final Random random = new Random();
    @Mod.EventBusSubscriber(modid = DeathbanMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void playerDeath(LivingDeathEvent event) {
            Entity entity = event.getEntity();
            if (entity instanceof ServerPlayer player) {
                UserBanList userBanList = Objects.requireNonNull(player.getServer()).getPlayerList().getBans();
                GameProfile gameProfile = player.getGameProfile();
                Date date = new Date();
                String banMessage = random.nextInt(50) == 0 ? "Toliko si loš da su te i roditelji ostavili Xdddddddd" : "Dosta za danas, vrijeme je za spavanac";
                UserBanListEntry userBanListEntry = new UserBanListEntry(gameProfile, null, player.getName().getContents(), (Date)date, banMessage);
                player.connection.disconnect(new TextComponent("You died!"));

                userBanList.add(userBanListEntry);
                player.connection.disconnect(new TextComponent(banMessage).withStyle(ChatFormatting.RED));
            }
        }
    }
}
