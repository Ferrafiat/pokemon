import {Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {Pokemons} from "../../model/pokemons";
//import { ItemsService } from '../../service/cart/cart.service';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})

export class ItemsComponent implements OnInit {

  @Input() pkm : Pokemons;

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
