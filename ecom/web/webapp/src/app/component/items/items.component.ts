import {Component, OnInit} from '@angular/core';
import {Pokemons} from "../../model/pokemons";
import { ItemsService } from '../../service/items/items.service';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})

export class ItemsComponent implements OnInit {

  private pkm : Pokemons;
  err: any;

  constructor(private itemsService: ItemsService) { }

  ngOnInit() {
    //this.pkm = new Pokemons();

    this.itemsService.all().subscribe(res => {
      this.pkm.translation = res.pokemons.translation;
      this.pkm.timestamp = res.pokemons.timestamp;
      this.pkm.language_id = res.pokemons.language_id;
      this.pkm.pokemon_specie_id = res.pokemons.pokemon_specie_id;
      this.err = res.err;
    });

  }

}
