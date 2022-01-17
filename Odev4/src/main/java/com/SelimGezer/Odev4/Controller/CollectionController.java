package com.SelimGezer.Odev4.Controller;

import com.SelimGezer.Odev4.Dtos.CollectionRequestDto;
import com.SelimGezer.Odev4.Dtos.CollectionResponceDto;
import com.SelimGezer.Odev4.Services.CollectionEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/collections")
public class CollectionController {

    private final CollectionEntityService collectionEntityService;

    public CollectionController(CollectionEntityService collectionEntityService) {
        this.collectionEntityService = collectionEntityService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addCollection(@RequestBody CollectionRequestDto collectionRequestDto){
            return collectionEntityService.addCollection(collectionRequestDto);
    }

    @GetMapping("/collection")
    public List<CollectionResponceDto> getAllCollectionBetweenDate(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date firstDate,
                                                                   @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        return collectionEntityService.getAllCollectionBetweenDate(firstDate,endDate);
    }

    @GetMapping("/collection/{id}")
    public List<CollectionResponceDto> getAllCollectionByUserId(@PathVariable("id") Long id){
        return collectionEntityService.getAllCollectionByUserId(id);
    }

    @GetMapping("/collection/latefee/{id}")
    public String getAllLateFeeAmountByUserId(@PathVariable("id") Long id){
        return collectionEntityService.getAllLateFeeAmountByUserId(id);
    }

}
