package web.dao;

import org.springframework.stereotype.Component;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class carDAO {
    private static int CAR_COUNT;
    private List<Car> cars;
    {
        cars = new ArrayList<>();
        cars.add(new Car(++CAR_COUNT,"Audi", 249, "black"));
        cars.add(new Car(++CAR_COUNT,"Lada",77,"Baklazan"));
        cars.add(new Car(++CAR_COUNT,"Mers", 200, "white"));
        cars.add(new Car(++CAR_COUNT,"Scoda",180, "red"));
        cars.add(new Car(++CAR_COUNT,"Lamba",999, "purple"));
    }

    public List<Car> index(){

        return cars;
    }

    public Car show(int id) {

        return cars.stream().filter(car -> car.getId() == id).findAny().orElse(null);
    }

    public void save (Car car){
        car.setId(++CAR_COUNT);
        cars.add(car);

    }

    public void update(int id, Car updateCar) {
        Car carToBeUpdated = show(id);
        carToBeUpdated.setName(updateCar.getName());
        carToBeUpdated.setPower(updateCar.getPower());
        carToBeUpdated.setColor(updateCar.getColor());
    }
    public void delete(int id) {
        cars.removeIf(p->p.getId()==id);
    }

    public Object showall(int count) {
       if (count == 0) {
            return cars;
        }
        return cars.stream().limit(count).collect(Collectors.toList());
    }
}
