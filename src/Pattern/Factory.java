package Pattern;

public class Factory {
}


abstract class BMW{
    public BMW(){}
}

class BMW320 extends BMW{
    public BMW320(){
        System.out.println("BMW320");
    }
}

class BMW523 extends BMW{
    public BMW523(){
        System.out.println("BMW523");
    }
}

/**
 * 简单工厂模式
 * 开闭原则：对扩展开放，对修改关闭
 * 简单工厂模式违背了开闭原则，因为如果要新增一种车型，
 * 都要在工厂类中增加相应的创建业务逻辑(即增加case)
 */
class FactorySimple{
    public BMW createBMW(int type){
        switch (type){
            case 320:
                return new BMW320();
            case 523:
                return new BMW523();
            default:
                    break;
        }
        return null;
    }
}


/**
 * 工厂方法模式
 * 产品类同上
 */
interface FactoryBMW{
    BMW createBMW();
}

class FactoryBMW320 implements FactoryBMW{
    public BMW createBMW() {
        return new BMW320();
    }
}

class FactoryBMW523 implements FactoryBMW{
    public BMW createBMW(){
        return new BMW523();
    }
}


/**
 * 抽象工厂模式
 * 每个车有不同的引擎和空调系统
 */
abstract class Engine{}

class EngineA extends Engine {
    public EngineA(){
        System.out.println("EngineA");
    }
}

class EngineB extends Engine{
    public EngineB(){
        System.out.println("EngineB");
    }
}

abstract class Aircondition{}

class AirconditionA extends Aircondition{
    public AirconditionA(){
        System.out.println("AirconditionA");
    }
}

class AirconditionB extends Aircondition{
    public AirconditionB(){
        System.out.println("AircondotionB");
    }
}

interface AbstractFactory{
    //制造发动机
    Engine createEngine();
    //制造空调
    Aircondition createAircondition();
}

class FactoryBMW320A implements AbstractFactory{
    public Engine createEngine(){
        return new EngineA();
    }
    public Aircondition createAircondition(){
        return new AirconditionA();
    }
}
class FactoryBMW523A implements AbstractFactory{
    public Engine createEngine(){
        return new EngineB();
    }
    public Aircondition createAircondition(){
        return new AirconditionB();
    }
}




class Customer{
    public static void main(String[] args) {
        //简单工厂
        FactorySimple factory = new FactorySimple();
        BMW bmw320 = factory.createBMW(320);
        BMW bmw523 = factory.createBMW(523);


        //工厂方法模式
        FactoryBMW factory320 = new FactoryBMW320();
        BMW bmw3201 = factory320.createBMW();

        FactoryBMW factory523 = new FactoryBMW523();
        BMW bmw5231 = factory523.createBMW();


        //抽象工厂模式
        FactoryBMW320A factoryBMW320A = new FactoryBMW320A();
        factoryBMW320A.createEngine();
        factoryBMW320A.createAircondition();

        //生产宝马523系列配件
        FactoryBMW523A factoryBMW523A = new FactoryBMW523A();
        factoryBMW523A.createEngine();
        factoryBMW523A.createAircondition();
    }
}


/**
 * 总结：
 *      都属于工厂模式，最终目的都是为了解耦
 *      不必在意属于哪种方法模式，只需要关心有没有降低耦合度
 */




