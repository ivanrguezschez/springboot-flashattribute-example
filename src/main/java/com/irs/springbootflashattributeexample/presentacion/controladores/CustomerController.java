package com.irs.springbootflashattributeexample.presentacion.controladores;

import com.irs.springbootflashattributeexample.negocio.dtos.CustomerDto;
import com.irs.springbootflashattributeexample.negocio.servicios.CustomerService;
import com.irs.springbootflashattributeexample.presentacion.comandos.CustomerEditCommand;
import com.irs.springbootflashattributeexample.util.I18n;
import com.irs.springbootflashattributeexample.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final I18n i18n;

    private final CustomerService customerService;

    private final Mapper<CustomerEditCommand, CustomerDto> customerEditCommandMapper;

    private final Validator customerEditCommandValidator;

    @GetMapping("/list")
    public String listHandler(Model model) {
        if (log.isDebugEnabled()) {
            log.debug("Listando clientes");
        }
        model.addAttribute("customers", this.customerService.findAll());

        return "customerSearch";
    }

    @GetMapping("/add")
    public String addHandler(Model model) {
        if (log.isDebugEnabled()) {
            log.debug("Añadiendo cliente");
        }
        model.addAttribute("customerEditCommand", new CustomerEditCommand());

        return "customerEdit";
    }

    @GetMapping("/edit/{id}")
    public String editHandler(@PathVariable Long id, Model model) {
        if (log.isDebugEnabled()) {
            log.debug("Editando cliente '{}'", id);
        }

        CustomerDto customerDto = this.customerService.findById(id);
        CustomerEditCommand customerEditCommand = this.customerEditCommandMapper.toSource(customerDto);

        model.addAttribute("customerEditCommand", customerEditCommand);

        return "customerEdit";
    }

    @PostMapping("/save")
    public String saveHandler(@ModelAttribute CustomerEditCommand customerEditCommand,
                              BindingResult result, SessionStatus status,
                              final RedirectAttributes redirectAttributes) {
        if (log.isDebugEnabled()) {
            log.debug("Guardando o actualizando cliente");
        }

        customerEditCommandValidator.validate(customerEditCommand, result);
        if (result.hasErrors()) {
            if (log.isDebugEnabled()) {
                log.debug("Comando CustomerEditCommand invalido");
            }
            return "customerEdit";
        }

        CustomerDto customerDto = this.customerEditCommandMapper.toTarget(customerEditCommand);
        String messageKey = null;

        if (Objects.isNull(customerDto.getId())) {
            this.customerService.save(customerDto);
            messageKey = "info.message.save.customer.success";
        } else {
            this.customerService.update(customerDto);
            messageKey = "info.message.update.customer.success";
        }
        status.setComplete();

        redirectAttributes.addFlashAttribute("globalMessage", i18n.getMessage(messageKey));

        return "redirect:/customers/list";
    }

    @GetMapping("/view/{id}")
    public String viewHandler(@PathVariable Long id, Model model) {
        if (log.isDebugEnabled()) {
            log.debug("Visualizando detalle cliente '{}'", id);
        }

        model.addAttribute("customer", this.customerService.findById(id));

        return "/customerView";
    }

    @GetMapping("/delete/{id}")
    public String deleteHandler(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        if (log.isDebugEnabled()) {
            log.debug("Eliminando cliente '{}'", id);
        }
        this.customerService.remove(id);

        redirectAttributes.addFlashAttribute("globalMessage", i18n.getMessage("info.message.remove.customer.success"));

        return "redirect:/customers/list";
    }
}
