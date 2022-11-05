*****************************    BananesExport    *****************************

ce projet a été réalisé dans le cadre de recrutement pour l'entreprise aios
répondant à l'énoncé suivante : https://gitlab.com/aios-sh/BananesExport/-/blob/master/Back/README.md
Développeur  : Yassine mouttalib
date : 05/11/2022
Le choix technique : la base de donnée  H2, hibernate, java 11, Mockito, springboot 2.7
Les APIs disponibles:
http://localhost:8080/receivers
http://localhost:8080/addReceiver
http://localhost:8080/updateReceiver
http://localhost:8080/deleteReceiver/{id}

http://localhost:8080/getAllOrders/{receiver_id}
http://localhost:8080/addOrder
http://localhost:8080/updateOrder
http://localhost:8080//deleteOrder/{id}

On a pas besoin de spécifier l'id des object receiver et order,
uniquement lorsqu'on veut créer une commande(order) il faut renseigner
l'Id de destinataire(receiver)

Exemple de jeu de données qu'on peut utiliser pour faire des tests sur postman.

http://localhost:8080/addReceiver
{
	"nom": "alain ",
	"adresse": "145 rue de la belge",
	"code_postal": "31000",
	"ville": "te",
	"pays": "France "
}

http://localhost:8080/updateReceiver/1
{
	"nom": "alain 1",
	"villes": "rouen"

}
http://localhost:8080/addOrder
{
	"receiver_id": "2",
	"delivery_date": "2022-11-13",
	"quantity": "1000",
	"prices": "2.5"
}

