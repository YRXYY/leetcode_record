package javalanguage.reflect;


import io.netty.util.internal.StringUtil;

import java.util.Map;

/**
 * 常规写法，判断条件是否满足
 * 但是如果后面需求变更了，这个修改起来会有些麻烦，比如
 * 需要盔甲是魔女斗篷的时候开火，or原来红色符文喝魔法防御满足其一，改成都要满足才开火，且小兵增加几个属性：召唤师技能、被动，需要判断召唤师技能为某种类型时开火。
 */
public class OpenFireImpl implements OpenFire {


    PreciseLocateService preciseLocateService;

    @Override
    public void openFire(Batman batman) {

        //方案1=============================================
        //if the rune is red, open the fire
        Map<String, String> magicArts;
        //如果是红色符文，或者防御是魔法防御，就开火
        if (batman.getRune().equalsIgnoreCase("red")
            || (!(magicArts = batman.getMagicArts()).isEmpty() && magicArts.get("defense").equalsIgnoreCase("magic"))) {
            System.out.println("open fire:" + batman.getId());
        }

        //方案2=============================================
        if (preciseLocateService.locateTarget(Batman.class, batman, "rune", "red")){
            System.out.println("open fire:" +batman.getId());
        }


    }
}
