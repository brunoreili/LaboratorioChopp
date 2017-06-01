var app = angular.module("botapp",[]);

app.controller("botctrl", function($scope, $http){
    
    $scope.enviar = function(texto){
        $scope.enviando=true;
      
        
        var update = {};
        update.message = {};
        update.message.text = texto;
        update.message.from = {};
        update.message.from.id = 1;
        
        
        $http.post("http://localhost:8080/update",update)
                .then(function(result){
                    console.log('result');
                    console.log(result);
                   $scope.enviando=false;
                   $scope.mensagem="Enviado!";
                 $scope.resposta = result;
                   console.log(result);
                },  function(erro){
                    console.log('erroo');
            console.log(erro);
                   $scope.enviando=false;
                   $scope.mensagem="Erro no Envio!"
                   console.log(erro);
                });
        console.log("foi!");
        
    }
    
});