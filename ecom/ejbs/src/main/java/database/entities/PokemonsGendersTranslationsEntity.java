package database.entities;

import javax.persistence.*;

@Entity
@Table(name = "pokemons_genders_translations", schema = "public", catalog = "pokemondb")
@IdClass(PokemonsGendersTranslationsEntityPK.class)
public class PokemonsGendersTranslationsEntity {
    private int genderId;
    private int languageId;
    private String translation;
    private PokemonsGendersEntity pokemonsGendersByGenderId;
    private LanguagesEntity languagesByLanguageId;

    @Id
    @Column(name = "gender_id", nullable = false)
    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    @Id
    @Column(name = "language_id", nullable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Id
    @Column(name = "translation", nullable = false, length = 50)
    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokemonsGendersTranslationsEntity that = (PokemonsGendersTranslationsEntity) o;

        if (genderId != that.genderId) return false;
        if (languageId != that.languageId) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genderId;
        result = 31 * result + languageId;
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false)
    public PokemonsGendersEntity getPokemonsGendersByGenderId() {
        return pokemonsGendersByGenderId;
    }

    public void setPokemonsGendersByGenderId(PokemonsGendersEntity pokemonsGendersByGenderId) {
        this.pokemonsGendersByGenderId = pokemonsGendersByGenderId;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLanguageId() {
        return languagesByLanguageId;
    }

    public void setLanguagesByLanguageId(LanguagesEntity languagesByLanguageId) {
        this.languagesByLanguageId = languagesByLanguageId;
    }
}
