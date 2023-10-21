describe('Hopitaux', () => {
    it('Visiter la page hopitaux et avoir lhopital le plus proche', () => {
        cy.visit('/hopitaux')


        cy.get('#username').type('sophie');
        cy.get('#password').type('jean');
        cy.get('[data-cy="submit"]').click();

        cy.contains('h1', 'Hopitaux')

        cy.get('#adresse').type('49 Tamworth St, Oldham OL9 7QY')
        cy.get('#specialite').select('Anesthésie')


        cy.get('form').submit()

        cy.contains('L\'hopital le plus proche est le suivant :')

        cy.get('ul li').should('have.length.gt', 0)


        cy.get('#supp').click()


        cy.get('ul li').should('have.length.gt', 1)



        cy.get('#reserver').click()


    })

    it('Doit se déconnecter avec succès', () => {

        cy.visit('/hopitaux')


        cy.get('#username').type('sophie');
        cy.get('#password').type('jean');
        cy.get('[data-cy="submit"]').click();

        cy.contains('h1', 'Hopitaux')

        cy.get('#deconnexion').click()
    
        cy.url().should('include', '/');
      })


})