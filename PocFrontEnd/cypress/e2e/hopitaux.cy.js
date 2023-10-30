describe('Hopitaux', () => {
    beforeEach(() => {

        cy.intercept('GET', '/hopitaux', { fixture: 'hopitaux.json' });

        cy.intercept('GET', '/hopitaux/hopitaux-proches*', {
            fixture: 'hopitaux-proches.json' 
          }).as('getHopitauxProches');

          cy.intercept('POST', '/patient/deconnexion', {
            statusCode: 200,
            body: {
              success: true,
              message: "Déconnexion réussie"  
            }
          }).as('deconnexion');

        cy.visit('/')

        cy.intercept('POST', '/patient/login', {
            statusCode: 200,
            body: {
              success: true,
              message: 'Connexion réussie'
            }
          }).as('loginRequest');

        cy.fixture('patient.json').then((patients) => {
            const patient = patients[0];
            cy.get('#username').type(patient.username);
            cy.get('#password').type(patient.password);
           cy.get('[data-cy="submit"]').click();

           localStorage.setItem('lepatient', 1)
           localStorage.setItem('session', 123)

            cy.wait('@loginRequest');
     
          });

          cy.intercept('GET', '/patient/deconnexion', {
            statusCode: 200,
            body: {
              success: true,
              message: "Déconnexion réussie"  
            }
          }).as('logout');
      })
  
    it('Visiter la page hopitaux et avoir lhopital le plus proche', () => {
        cy.visit('/Hopitaux');
        
     cy.contains('h1', 'Hopitaux')
  
      cy.get('#adresse').type('49 Tamworth St, Oldham OL9 7QY')
      cy.get('#specialite').select('Anesthésie')
  
      cy.get('form').submit()

      cy.wait('@getHopitauxProches');
  
      cy.contains('L\'hopital le plus proche est le suivant :')
  
      cy.get('ul li').should('have.length.gt', 0)
  
      cy.get('#supp').click()
  
      cy.get('ul li').should('have.length.gt', 1)
  
      cy.get('#reserver').click()

    })
  
    it('Doit se déconnecter avec succès', () => {
        cy.visit('/Hopitaux');
      cy.contains('h1', 'Hopitaux')
  
      cy.get('#deconnexion').click()

      cy.wait('@logout');
  
      cy.url().should('include', '/');
    })
  })