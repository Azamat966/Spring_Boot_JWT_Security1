package com.example.spring_boot_jwt_security.api;

import com.example.spring_boot_jwt_security.dto.request.ClientRequest;
import com.example.spring_boot_jwt_security.dto.request.CompanyRequest;//Azamat7
import com.example.spring_boot_jwt_security.dto.response.ClientResponse;
import com.example.spring_boot_jwt_security.model.Client;
import com.example.spring_boot_jwt_security.model.Company;
import com.example.spring_boot_jwt_security.model.Orders;
import com.example.spring_boot_jwt_security.repository.OrdersRepository;
import com.example.spring_boot_jwt_security.service.ClientService;
import com.example.spring_boot_jwt_security.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class ClientApi {
    private final ClientService service;
    private final OrdersRepository ordersRepository;


    @PostMapping("/api/v1/client/save")
    public String saveClient(@RequestBody ClientRequest request){
        service.save(request);
        return "Saved";
    }
    @GetMapping("/api/v1/client/find/all")
    public List<Client> getAll(){return service.findAll();}


    @GetMapping("/api/v1/client/find/by{id}")
    public ClientResponse getById(@PathVariable Long id){return service.getById(id);}

    @DeleteMapping("/api/v1/client/delete/{id}")
    public  String deleteById(@PathVariable Long id){
        service.deleteByID(id);
        return "deleted sucsecsful";
    }
    @GetMapping("/find/orders")
    @ApiOperation(value = "Count orders", notes = "Count orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully counted"),
            @ApiResponse(code = 404, message = "Orders not found")
    })
    public ResponseEntity<OrderCountResponse> countOrders() {
        int count = countor();
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        OrderCountResponse response = new OrderCountResponse(count);
        return ResponseEntity.ok(response);
    }

    // Метод для подсчета заказов
    private int countor() {
        int count = 0;
        for (Orders order : ordersRepository.findAll()) {
            count++;
        }
        return count;
    }

    // Класс-обертка для ответа с количеством заказов
    public static class OrderCountResponse {
        private int countOrders;

        public OrderCountResponse(int countOrders) {
            this.countOrders = countOrders;
        }

        public int getCountOrders() {
            return countOrders;
        }

        public void setCountOrders(int countOrders) {
            this.countOrders = countOrders;
        }
    }
}
