package javalanguage.reflect;


import io.netty.util.internal.StringUtil;

import java.util.Map;

/**
 * ����д�����ж������Ƿ�����
 * ������������������ˣ�����޸���������Щ�鷳������
 * ��Ҫ������ħŮ�����ʱ�򿪻�orԭ����ɫ���ĺ�ħ������������һ���ĳɶ�Ҫ����ſ�����С�����Ӽ������ԣ��ٻ�ʦ���ܡ���������Ҫ�ж��ٻ�ʦ����Ϊĳ������ʱ����
 */
public class OpenFireImpl implements OpenFire {


    PreciseLocateService preciseLocateService;

    @Override
    public void openFire(Batman batman) {

        //����1=============================================
        //if the rune is red, open the fire
        Map<String, String> magicArts;
        //����Ǻ�ɫ���ģ����߷�����ħ���������Ϳ���
        if (batman.getRune().equalsIgnoreCase("red")
            || (!(magicArts = batman.getMagicArts()).isEmpty() && magicArts.get("defense").equalsIgnoreCase("magic"))) {
            System.out.println("open fire:" + batman.getId());
        }

        //����2=============================================
        if (preciseLocateService.locateTarget(Batman.class, batman, "rune", "red")){
            System.out.println("open fire:" +batman.getId());
        }


    }
}
