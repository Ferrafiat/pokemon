package database.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "languages", schema = "public", catalog = "pokemondb")
public class LanguagesEntity {
    private int id;
    private String name;
    private Collection<ItemsCategoriesTranslationsEntity> itemsCategoriesTranslationsById;
    private Collection<ItemsDescriptionsTranslationsEntity> itemsDescriptionsTranslationsById;
    private Collection<ItemsNamesTranslationsEntity> itemsNamesTranslationsById;
    private Collection<PokemonsColorsTranslationsEntity> pokemonsColorsTranslationsById;
    private Collection<PokemonsDescriptionsTranslationsEntity> pokemonsDescriptionsTranslationsById;
    private Collection<PokemonsGendersTranslationsEntity> pokemonsGendersTranslationsById;
    private Collection<PokemonsHabitatsTranslationsEntity> pokemonsHabitatsTranslationsById;
    private Collection<PokemonsShapesTranslationsEntity> pokemonsShapesTranslationsById;
    private Collection<PokemonsSpeciesTranslationsEntity> pokemonsSpeciesTranslationsById;
    private Collection<RolesTranslationsEntity> rolesTranslationsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagesEntity that = (LanguagesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<ItemsCategoriesTranslationsEntity> getItemsCategoriesTranslationsById() {
        return itemsCategoriesTranslationsById;
    }

    public void setItemsCategoriesTranslationsById(Collection<ItemsCategoriesTranslationsEntity> itemsCategoriesTranslationsById) {
        this.itemsCategoriesTranslationsById = itemsCategoriesTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<ItemsDescriptionsTranslationsEntity> getItemsDescriptionsTranslationsById() {
        return itemsDescriptionsTranslationsById;
    }

    public void setItemsDescriptionsTranslationsById(Collection<ItemsDescriptionsTranslationsEntity> itemsDescriptionsTranslationsById) {
        this.itemsDescriptionsTranslationsById = itemsDescriptionsTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<ItemsNamesTranslationsEntity> getItemsNamesTranslationsById() {
        return itemsNamesTranslationsById;
    }

    public void setItemsNamesTranslationsById(Collection<ItemsNamesTranslationsEntity> itemsNamesTranslationsById) {
        this.itemsNamesTranslationsById = itemsNamesTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsColorsTranslationsEntity> getPokemonsColorsTranslationsById() {
        return pokemonsColorsTranslationsById;
    }

    public void setPokemonsColorsTranslationsById(Collection<PokemonsColorsTranslationsEntity> pokemonsColorsTranslationsById) {
        this.pokemonsColorsTranslationsById = pokemonsColorsTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsDescriptionsTranslationsEntity> getPokemonsDescriptionsTranslationsById() {
        return pokemonsDescriptionsTranslationsById;
    }

    public void setPokemonsDescriptionsTranslationsById(Collection<PokemonsDescriptionsTranslationsEntity> pokemonsDescriptionsTranslationsById) {
        this.pokemonsDescriptionsTranslationsById = pokemonsDescriptionsTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsGendersTranslationsEntity> getPokemonsGendersTranslationsById() {
        return pokemonsGendersTranslationsById;
    }

    public void setPokemonsGendersTranslationsById(Collection<PokemonsGendersTranslationsEntity> pokemonsGendersTranslationsById) {
        this.pokemonsGendersTranslationsById = pokemonsGendersTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsHabitatsTranslationsEntity> getPokemonsHabitatsTranslationsById() {
        return pokemonsHabitatsTranslationsById;
    }

    public void setPokemonsHabitatsTranslationsById(Collection<PokemonsHabitatsTranslationsEntity> pokemonsHabitatsTranslationsById) {
        this.pokemonsHabitatsTranslationsById = pokemonsHabitatsTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsShapesTranslationsEntity> getPokemonsShapesTranslationsById() {
        return pokemonsShapesTranslationsById;
    }

    public void setPokemonsShapesTranslationsById(Collection<PokemonsShapesTranslationsEntity> pokemonsShapesTranslationsById) {
        this.pokemonsShapesTranslationsById = pokemonsShapesTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<PokemonsSpeciesTranslationsEntity> getPokemonsSpeciesTranslationsById() {
        return pokemonsSpeciesTranslationsById;
    }

    public void setPokemonsSpeciesTranslationsById(Collection<PokemonsSpeciesTranslationsEntity> pokemonsSpeciesTranslationsById) {
        this.pokemonsSpeciesTranslationsById = pokemonsSpeciesTranslationsById;
    }

    @OneToMany(mappedBy = "languagesByLanguageId")
    public Collection<RolesTranslationsEntity> getRolesTranslationsById() {
        return rolesTranslationsById;
    }

    public void setRolesTranslationsById(Collection<RolesTranslationsEntity> rolesTranslationsById) {
        this.rolesTranslationsById = rolesTranslationsById;
    }
}
