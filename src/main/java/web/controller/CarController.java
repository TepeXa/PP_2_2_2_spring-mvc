package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.carDAO;
import web.models.Car;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/")
public class CarController {

	private final carDAO carDAO;
	@Autowired
	public CarController(carDAO carDAO) {
		this.carDAO = carDAO;
	}

	@GetMapping()
	public String index(Model model){
		model.addAttribute("car", carDAO.index());
		//получим всех людей из DAO и передадим на отображение в представление
		return "index";
	}

	@GetMapping ("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("car", carDAO.show(id));
		//Получим одного человека по ID из DAO и передадим на отображение и представление
		return "show";
	}

	@GetMapping("/new")
	public String newCar(@ModelAttribute("car") Car car) {
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "new";
		carDAO.save(car);
		return "redirect:index";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id){
		model.addAttribute("car",carDAO.show(id));
		return "edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("car") @Valid Car car,BindingResult bindingResult ,@PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "edit";
		carDAO.update(id, car);
		return "redirect:index";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		carDAO.delete(id);
		return "redirect:index";
	}
}