package tk.mwacha.infrastructure.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tk.mwacha.usecase.product.create.CreateProductUseCase;
import tk.mwacha.usecase.product.create.InputCreateProductDTO;
import tk.mwacha.usecase.product.create.OutputCreateProductDTO;
import tk.mwacha.usecase.product.list.InputListProductDTO;
import tk.mwacha.usecase.product.list.ListProductUseCase;
import tk.mwacha.usecase.product.list.OutputListProductDTO;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listProductUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OutputCreateProductDTO create(@RequestBody InputCreateProductDTO dto) {
        return createProductUseCase.execute(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OutputListProductDTO list(@RequestBody InputListProductDTO dto) {
       return listProductUseCase.execute(dto);
    }
}
