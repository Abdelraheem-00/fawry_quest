interface Engine {
    void start();
    void stop();
    void setSpeed(int speed);
}

class GasEngine implements Engine {
    public void start() {
        System.out.println("Gas engine started.");
    }

    public void stop() {
        System.out.println("Gas engine stopped.");
    }

    public void setSpeed(int speed) {
        System.out.println("Gas engine running at " + speed + " km/h.");
    }
}

class ElectricEngine implements Engine {
    public void start() {
        System.out.println("Electric engine started.");
    }

    public void stop() {
        System.out.println("Electric engine stopped.");
    }

    public void setSpeed(int speed) {
        System.out.println("Electric engine running at " + speed + " km/h.");
    }
}

class HybridEngine implements Engine {
    private GasEngine gasEngine = new GasEngine();
    private ElectricEngine electricEngine = new ElectricEngine();
    private Engine currentEngine;
    private int currentSpeed = 0;

    public void start() {
        currentEngine = electricEngine;
        currentEngine.start();
    }

    public void stop() {
        currentEngine.stop();
    }

    public void setSpeed(int speed) {
        this.currentSpeed = speed;
        if (speed < 50) {
            if (!(currentEngine instanceof ElectricEngine)) {
                gasEngine.stop();
                electricEngine.start();
                currentEngine = electricEngine;
            }
        } else {
            if (!(currentEngine instanceof GasEngine)) {
                electricEngine.stop();
                gasEngine.start();
                currentEngine = gasEngine;
            }
        }
        currentEngine.setSpeed(speed);
    }
}

class Car {
    private Engine engine;
    private int speed = 0;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        System.out.println("Car starting...");
        engine.start();
    }

    public void stop() {
        System.out.println("Car stopping...");
        engine.stop();
        speed = 0;
    }

    public void accelerate() {
        if (speed < 200) {
            speed += 20;
        }
        System.out.println("Accelerating to " + speed + " km/h.");
        engine.setSpeed(speed);
    }

    public void brake() {
        if (speed > 0) {
            speed -= 20;
        }
        System.out.println("Braking to " + speed + " km/h.");
        engine.setSpeed(speed);
    }
}

enum EngineType {
    GAS, ELECTRIC, HYBRID
}

class CarFactory {
    public static Car createCar(EngineType type) {
        Car car = new Car();
        Engine engine;
        switch (type) {
            case GAS:
                engine = new GasEngine();
                break;
            case ELECTRIC:
                engine = new ElectricEngine();
                break;
            case HYBRID:
                engine = new HybridEngine();
                break;
            default:
                throw new IllegalArgumentException("Invalid engine type.");
        }
        car.setEngine(engine);
        return car;
    }

    public static void replaceEngine(Car car, EngineType type) {
        car.setEngine(createCar(type).engine);
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = CarFactory.createCar(EngineType.HYBRID);
        car.start();
        car.accelerate();
        car.accelerate();
        car.brake();
        car.stop();
    }
}
