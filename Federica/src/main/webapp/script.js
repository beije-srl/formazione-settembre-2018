//tra parentesi quadre posso dichiarare altri moduli tipo i coookies
var app = angular.module("listaSpesa",[]);
//chiamo il controller presente anche nella pagina html,tra parentesi metto il nome del controller e poi 'eventuale function
app.controller("headerController", function($scope){
	$scope.nome= "Luigi";
	$scope.cognome="Boccia";
	$scope.cambiaNome= function(){
	$scope.nome = "Domenico";
		}
	});