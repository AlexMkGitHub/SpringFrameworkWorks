import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductService} from "../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", null);

  isError = false;

  errorMessage = "";

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) { }


  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.productService.findById(param['id'])
        .subscribe(res => {
          this.product = res;
        }, err => {
          console.error(err);
        })
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe(res => {
        console.log(res);
        this.isError = false;
        this.errorMessage = "";
        this.router.navigateByUrl(`/product`)
      }, err => {
        console.error(err);
        this.isError = true;
        this.errorMessage = err.error;
      })
  }

}
