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
            },function(erro){
                console.log('erroo');
                console.log(erro);
                $scope.enviando=false;
                $scope.mensagem="Erro no Envio!"
                console.log(erro);
            });
        console.log("foi!");
    }
    
    $scope.app = "Cadastro de Cervejas";
    $scope.cervejas = [];
    
    $scope.adcionarCerveja = function (cerveja) {
        //$scope.cervejas.push(angular.copy(cerveja));
        delete $scope.cerveja;
        $scope.enviando=true;
        console.log("savandoooo");
        console.log(cerveja);
        $http.post("http://localhost:8080/salvar", cerveja)
        .then(function(){
            console.log("foieee")
            $scope.enviando=false;
            $scope.mensagem="Cerveja cadastrada com sucesso!";
        }, function(){
            console.log("erro")
            $scope.enviando=false;
            $scope.mensagem="Erro no cadastro!"
        });
    }
    
    $scope.listar = function(){
        //$scope.cervejas.push(angular.copy(cerveja));
        $scope.buscando = true;
        console.log("buscandooOOOoo");
        $http.get("http://localhost:8080/listar")
                .then(function(cerveja){
                   $scope.buscando=false;
                   console.log(cerveja);
                   $scope.cervejas = cerveja.data;
                },function(erro){
                   $scope.buscando=false;
                   console.log(erro);
                })        
    }

});