//Definizione modulo
var app = angular.module("Lista_Spesa" , []); //All'interno dell quadre possiamo inserire il nome del file che vogliamo aggiungere 


app.controller("headerController", function ($scope){ //$hhtp $location $scope
	
	$scope.Lista = [];
	$scope.id = 0;
	$scope.n = '';
	$scope.q = '';
	$scope.p = '';
	$scope.d = '';
	
	$scope.settaNomi = function () {
		
		if($scope.n != '' && $scope.q != '' && $scope.p != ''  && $scope.d != '' ){
			var MioOggetto = {
				
					id : $scope.id,
					nome : $scope.n,
					quantita : $scope.q,
					prezzo : $scope.p,
					descrizione : $scope.d
			}
			
			$scope.Lista.push(MioOggetto);
			$scope.id++;
		}
		else{
			alert("I dati non sono stati inseriti correttamente");
		}
	};
	
});

