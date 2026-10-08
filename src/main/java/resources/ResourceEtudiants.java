
package resources;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class ResourceEtudiants {

    EtudiantBusiness etudiantBusiness = new EtudiantBusiness();
    OptionBusiness optionBusiness = new OptionBusiness();



    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        if (etudiantBusiness.addEtudiant(etudiant)) {
            return Response.status(Response.Status.OK).entity("created successfully").build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getAll() {

        return etudiantBusiness.getAllEtudiants();
    }



    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(
            @PathParam("identifiant") String identifiant) {

        Etudiant etudiant =
                etudiantBusiness.getEtudiantByIdentifiant(identifiant);

        if (etudiant != null) {
            return Response.status(Response.Status.OK).entity(etudiant).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(
            @PathParam("identifiant") String identifiant) {

        if (etudiantBusiness.deleteEtudiant(identifiant)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @PUT
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("identifiant") String identifiant,
            Etudiant updatedEtudiant) {

        if (etudiantBusiness.updateEtudiant(identifiant, updatedEtudiant)) {

            return Response.status(Response.Status.OK).entity(etudiantBusiness.getEtudiantByIdentifiant(identifiant)).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }



    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {


        Option option = optionBusiness.getOptionByCode(codeOption);


        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


        List<Etudiant> etudiants =
                etudiantBusiness.getEtudiantsByOption(option);

        return Response.status(Response.Status.OK).entity(etudiants).build();
    }

}
