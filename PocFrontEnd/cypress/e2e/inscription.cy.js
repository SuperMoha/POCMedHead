describe('Inscription', () => {
    it('Visiter la page dinscription et sinscrire avec success', () => {
      cy.visit('/Inscription')
      cy.contains('h1', 'Inscription')

      cy.get('#nom').type('John');
    cy.get('#prenom').type('Doe');
    cy.get('#age').type('30');
    cy.get('#sexe').select('Male');
    cy.get('#adresse').type('123 Main St');
    cy.get('#numero').type('1234567890');
    cy.get('#username').type('johex1xe55naaoe');
    cy.get('#password').type('password123');
    cy.get('[data-cy=rgpd]').click();
    cy.get('#rgpd-consent').check();

    cy.get('[data-cy=submit]').click();

    cy.on('window:alert', (str) => {
        expect(str).to.equal('Inscription réussie !');
      });

    cy.url().should('include', '/');
    });

    it('Visiter la page dinscription et sinscrire sans rien remplir', () => {
        cy.visit('/Inscription');
    
        cy.get('[data-cy=submit]').click();

        cy.get('#nom:invalid').should('have.length', 1)
        cy.get('#prenom:invalid').should('have.length', 1)
        cy.get('#age:invalid').should('have.length', 1)
        cy.get('#adresse:invalid').should('have.length', 1)
        cy.get('#numero:invalid').should('have.length', 1)
        cy.get('#username:invalid').should('have.length', 1)
        cy.get('#password:invalid').should('have.length', 1)

        cy.on('window:alert', (str) => {
          expect(str).to.equal('Vous devez accepter les consignes RGPD.');
        });
      });

      it('Aller sur la page de connexion', () => {
        cy.visit('/Inscription');
    
        cy.get('[data-cy=connexion]').click();
    
        cy.url().should('include', '/');
      });

})