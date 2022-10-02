package tk.mwacha.infrastructure.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.usecase.customer.create.CreateCustomerUseCase;
import tk.mwacha.usecase.customer.create.InputCreateCustomerDTO;
import tk.mwacha.usecase.customer.create.OutputCreateCustomerDTO;
import tk.mwacha.usecase.customer.list.InputListCustomerDTO;
import tk.mwacha.usecase.customer.list.ListCustomerUseCase;
import tk.mwacha.usecase.customer.list.OutputListCustomerDTO;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomerUseCase listCustomerUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OutputCreateCustomerDTO create(@RequestBody InputCreateCustomerDTO dto) {
        return createCustomerUseCase.execute(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OutputListCustomerDTO list(@RequestBody InputListCustomerDTO dto) {
       return listCustomerUseCase.execute(dto);
    }
}
