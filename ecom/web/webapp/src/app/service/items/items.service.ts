import { Injectable } from '@angular/core';
import {Http} from '@angular/http';

import 'rxjs/Rx';
import {Observable} from 'rxjs';
import {Pokemons} from "../../model/pokemons";

@Injectable()
export class ItemsService {
  pokemons : Pokemons;

  constructor(private http:Http) { }

  public all(): Observable<Pokemons> {

    /*
    const pkm : Pokemons;

    pkm.pokemon_specie_id = 1;
    pkm.language_id = 8;
    pkm.timestamp = "2017-09-21T12:08:00.749Z";
    pkm.translation = "Bulbasaur"
    */

    return this.http.get(`http://152.77.78.29:9200/web/api/simple/pokemons_species_translations/translation/golbat`).map(res => res.json()._embedded.pokemons);
  }
}
