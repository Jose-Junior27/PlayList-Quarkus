package br.com.bb.genero;

public class GeneroMapper {

    public static GeneroDTO generoToDTO(Genero genero) {
        if (genero == null){
            return null;
        }
        return new GeneroDTO(genero.getUid(), genero.getNome());
    }

    public static Genero dtoToGenero(GeneroDTO generoDTO){
        var genero = new Genero();
        genero.setUid(generoDTO.getUid());
        genero.setNome(generoDTO.getNome());
        return genero;
    }

}
