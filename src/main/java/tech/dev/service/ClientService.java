package tech.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.dev.commons.service.base.AbstractCRUDServiceBase;
import tech.dev.dao.AdresseJpaDAO;
import tech.dev.dao.ClientJpaDAO;
import tech.dev.entites.Client;
import tech.dev.to.ClientTO;
import tech.dev.to.convertor.ClientTOConvertor;

import java.util.List;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Service
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClientService extends AbstractCRUDServiceBase<Client, ClientTO> {

    //private static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    ClientJpaDAO clientDAO;
    AdresseJpaDAO adresseDAO;

    @Autowired
    public ClientService(ClientJpaDAO clientDAO, AdresseJpaDAO adresseDAO) {
        this.clientDAO  = clientDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    protected ClientTOConvertor getConvertor() {
        if (convertor == null) {
            convertor = new ClientTOConvertor();
        }
        return (ClientTOConvertor) convertor;
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NullPointerException.class)
    public void deleteClientByAdresseId(Long id){
        //System.out.println(clientDAO);
        this.clientDAO.deleteByAdreseId(id);
        this.adresseDAO.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = NullPointerException.class)
    public void deleteClientByClientId(Long id){
        //System.out.println(clientDAO);
        this.clientDAO.deleteById(id);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public List<ClientTO> findAllFillTO() {
        List<Client> entityList = clientDAO.findAll();
        //convertor
        List<ClientTO> TOlist = getConvertor().entityList2TOList(entityList, false);

        return TOlist;
    }

    public List<Client> findClientsByAdresseId(Long id){
      return clientDAO.findClientsByAdresseId(id);
    }


    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public ClientTO findByIdFillTO(Long id) {
        Client client = clientDAO.findById(id);
        //convertor
        ClientTO to = getConvertor().entity2TO(client, false);
        return to;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Client client, boolean isCreation) {
        if (isCreation) {
            this.clientDAO.create(client);
        } else{
            this.clientDAO.update(client);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTO(ClientTO to, boolean isCreation) {
        Client client = null;
        // On récupère les données de la base avant de merger pour modifier uniquement ce qui provient du TO
        if (!isCreation) {
            client = findById(to.getId());
        }
        //convertor
        client = getConvertor().fillEntity(to, client);

        if (isCreation) {
            this.clientDAO.create(client);
        } else{
            this.clientDAO.update(client);
        }
    }

}
