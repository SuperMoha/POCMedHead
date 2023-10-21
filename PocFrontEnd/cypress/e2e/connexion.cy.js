describe('Connexion', () => {
  it('Visiter la page de connexion et se connecter', () => {
    cy.visit('/')
    cy.contains('h1', 'Connexion')

    cy.get('#username').type('sophie');
    cy.get('#password').type('jean');
    cy.get('[data-cy="submit"]').click();

    cy.url().should('include', '/hopitaux');
  })


  it('Afficher une erreur si les logs sont incorrects', () => {
    cy.visit('/');

    cy.get('#username').type('yohan'); 
    cy.get('#password').type('blabla');
    cy.get('[data-cy=submit]').click();

    cy.on('window:alert', (str) => {
      expect(str).to.equal('Nom dutilisateur ou mot de passe incorrect');
    });
  });

  it('Aller sur la page dinscription', () => {
    cy.visit('/');

    cy.get('[data-cy=inscription]').click();

    cy.url().should('include', '/Inscription');
  });
})
