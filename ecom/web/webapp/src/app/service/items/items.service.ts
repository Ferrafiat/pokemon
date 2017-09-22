import { Injectable } from '@angular/core';
import {Http} from '@angular/http';

import 'rxjs/Rx';
import {Observable} from 'rxjs';
import {Pokemons} from "../../model/pokemons";

@Injectable()
export class ItemsService {

  constructor(private http:Http) { }

  public all(): Observable<PokemonsResponse> {
    return this.http.get(`http://152.77.78.29:8080/web/api/pokelastic/simple/pokemons_species_translations/translation/golbat`)
      .map(res => {
        const body = res.json();
        return { err: null, pokemons: body };
      })
      .catch(err => {
        console.log('Server error: ' + JSON.stringify(err, null, 2));
        return Observable.of({err: err, species: null});
      });
  }
}

  export interface PokemonsResponse {
    err: any;
    pokemons: Pokemons;
  }
