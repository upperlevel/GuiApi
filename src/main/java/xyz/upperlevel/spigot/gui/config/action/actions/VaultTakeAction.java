package xyz.upperlevel.spigot.gui.config.action.actions;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import xyz.upperlevel.spigot.gui.SlimyGuis;
import xyz.upperlevel.spigot.gui.config.action.Action;
import xyz.upperlevel.spigot.gui.config.action.BaseActionType;
import xyz.upperlevel.spigot.gui.config.action.Parser;
import xyz.upperlevel.spigot.gui.config.economy.EconomyManager;
import xyz.upperlevel.spigot.gui.config.placeholders.PlaceholderValue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VaultTakeAction extends Action<VaultTakeAction> {
    public static final VaultTakeActionType TYPE = new VaultTakeActionType();
    @Getter
    private final PlaceholderValue<Double> value;
    @Getter
    private List<Action> actions;
    @Getter
    private List<Action> fail;

    public VaultTakeAction(PlaceholderValue<Double> value, List<Action> actions, List<Action> fail) {
        super(TYPE);
        this.value = value;
        this.actions = actions;
        this.fail = fail;
    }

    @Override
    public void run(Player player) {
        final Economy economy = EconomyManager.getEconomy();
        if(economy == null) {
            SlimyGuis.logger().severe("Cannot find vault's economy!");
            return;
        }
        if(economy.withdrawPlayer(player, value.get(player)).transactionSuccess()) {
            for(Action a : actions)
                a.run(player);
        } else
            for(Action a : fail)
                a.run(player);
    }


    public static class VaultTakeActionType extends BaseActionType<VaultTakeAction> {

        public VaultTakeActionType() {
            super("vault-take");
            setParameters(
                    Parameter.of("value", Parser.strValue(), true),//TODO: better Placeholder support
                    Parameter.of("actions", Parser.actionsValue(), false),
                    Parameter.of("fail", Parser.actionsValue(), Collections.emptyList(),false)
            );
        }

        @Override
        @SuppressWarnings("unchecked")//:(
        public VaultTakeAction create(Map<String, Object> pars) {
            return new VaultTakeAction(
                    PlaceholderValue.doubleValue((String) pars.get("value")),
                    (List<Action>)pars.get("actions"),
                    (List<Action>)pars.get("fail")
            );
        }

        @Override
        public Map<String, Object> read(VaultTakeAction action) {
            return ImmutableMap.of(
                    "value", action.value.toString(),
                    "action", action.actions,
                    "fail", action.fail
            );
        }
    }
}