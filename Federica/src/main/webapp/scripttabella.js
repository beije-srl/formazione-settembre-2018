var app = angular.module("Market", []);
var inserimento = {
Nome : "Nome",
Quantita : "Quantita",
Prezzo : "Prezzo",
Descrizione : "Descrizione"
};
var cancella = {
Nome : "Nome",
Quantita : "Quantita",
Prezzo : "Prezzo",
Descrizione : "Descrizione"

};

var nuovo = {
Nome : "Nome",
Quantita : "Quantita",
Prezzo : "Prezzo",
Descrizione : "Descrizione"
};
app.controller("prodotti", function($scope) {
$scope.inserimento = [ "Nome", "Quantita", "Prezzo", "Descrizione" ];
$scope.matrice = [];
$scope.inserisciValore = function() {
$scope.nuovo = [ $scope.nome, $scope.quantita, $scope.prezzo,
$scope.descrizione ];
$scope.matrice.push($scope.nuovo);
$scope.cancella = function () {
$scope.matrice.delete($scope.inserimento)

}
}
});
