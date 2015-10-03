PeerDroid Tutorial to understand the architecture and run the rendezvous(RDV) and sample applications.

# Introduction #

Peer Droid is the porting of JXME protocol to Android Platform. It allows the Android Developers to create application for Android platform that uses the features of JXTA system along with Android potential, interacting with other mobile terminals and other traditional peers (Personal-Computer).

For some information about JXTA you can see to official programmers guide at: [https://jxta-guide.dev.java.net/](https://jxta-guide.dev.java.net/).

JXTA Programmars Guide describes the following kind of peers:
  * **Minimal-Edge peers:** Peers that implement only the required core JXTA services and may rely on other peers to act as their proxy for other services to fully participate in a JXTA Network. The proxy peers act as proxy for the non-core services. Typical minimal-edge peers include sensor devices and home automation devices,
  * **Full-Edge Peer:** Peers that implements all of the core and standard JXTA services and can participate in all of the JXTA protocols. These peers form the majority of peers on a JXTA network and can include phones, PC's, servers, etc.
  * **Super-Peer:** Peers that implement and provision resources to support the deployment and operation of a JXTA network. There are three key JXTA Super Peer functions. A single peer may implement one or more of these functions.
  * **Relay:** Used to store and forward messages between peers that do not have direct connectivity because of firewalls or NAT. Only peers which are unable to receive connections from other peers require a relay.
  * **Rendezvous:** Maintains global advertisement indexes and assists edge and proxied peers with advertisement searches. Also handles message broadcasting.
  * **Proxy:** Used by minimal-edge peers to get access to all the JXTA network functionalities. The proxy peer translates and summarizes requests, responds to queries and provides support functionality for minimal-edge peers.

This tutorial shows how you can run a RDV and different nodes based on our porting with some traditional JXTA peers.

Available Applications and Software are:
  * peerdroid.jar
  * Peer Droid Sample Application with:
    * Service classes to manage in a easy way JXTA Connection,Socket,ADV and peerList
    * Simple GUI to show incoming messages, network's status and available peer list
  * JXTA Sample Application that you can run on traditional PCs with the same characteristics of the previous one but without the GUI.
  * A simple RDV that EDGE nodes can use to join a JXTA Network

This tutorial as our project is in a early phase and it is in developing following our last updates. We are waiting your feedbacks to fix bugs and improve features and characteristics of PeerDroid.

# RDV #

_"Maintains global advertisement indexes and assists edge and proxied peers with advertisement searches. Also handles message broadcasting." (From JXTA Guide)_

You can download RDV's codes from the Download Section or with the SVN Repository and import it with Eclipse or start it with the provided Ant script.
The RDV must be executed on a computer inside the  LAN where you are testing the P2P application or if you want you can run it on a machine with a public IP in order to be reached from everyone.

Peers that come into the network need to know the RDV's IP in order to contact him and Join into the JXTA system. For this reason you must provide a simple txt file (called in our examples rdvlist.txt ) that contains information about one or more rendezvous.

This is an example of "rdvlist.txt" where we are adding information about Ip and available ports:

tcp://160.78.28.131:9701 <br>
<a href='http://160.78.28.131:9700'>http://160.78.28.131:9700</a>

Link to this file will be used in PeerDroid application and also in traditional JXTA solution to reach our RDV. (This is a tradition approach for JXTA solutions)<br>
<br>
<h1>PeerDroid Sample Application</h1>

PeerDroid Sample Application is based on the porting of JXME for the Android's platform and provides a simple application to show the basic features.<br>
He has some Class that try to simplify the configuration of a JXTA's node and the comunications called JXTAService and SocketService. In the next release of peerdroid, methods of these classes will be improved to provide a complete set of functionality for delevopers.<br>
<br>
PeerDroidSample can be retrieved from Download section or from SVN and can be imported using Eclipse and hid Android plugin.<br>
Open peerdroid.sample.service.JXTAService and change the variable called <i>rdvlist</i> with the address of you RDV List defined above.<br>
<br>
<pre><code> private static String rdvlist = "http://www.example.come/rdvlist.txt";<br>
</code></pre>

The first method called after the initialization of variables is <i>"start()"</i>. In this method there are all configuration of JXTA peer like:<br>
<br>
NB: For the other configuration information you can see the JXTA Guide.<br>
<br>
<b>Peer Configuration</b>
<pre><code>config.setPeerID(IDFactory.newPeerID(PeerGroupID.defaultNetPeerGroupID));<br>
config.setName(instanceName);<br>
config.setDescription("Created by AndroidTester");<br>
config.setMode(NetworkConfigurator.EDGE_NODE);<br>
config.setPrincipal(principal);<br>
config.setPassword(password);<br>
config.setUseMulticast(false);<br>
</code></pre>

<b>NetWork Configuration</b>
<pre><code>netPeerGroup = networkManager.startNetwork();<br>
rendezvous = netPeerGroup.getRendezVousService();<br>
discovery = netPeerGroup.getDiscoveryService();<br>
</code></pre>

<b>Socket Service Configuration</b>
Create the service(One of our service classes) that allows to manage the incoming and outgoing connections based on JXTA Socket.<br>
<pre><code>socketService = new SocketService(this);<br>
</code></pre>

<b>Publish the Pipe Advertisment</b>
Publish the Pipe Advertisment, starting from the SocketService.<br>
<pre><code>discovery.publish(socketService.getPipeAdv());<br>
discovery.remotePublish(socketService.getPipeAdv());<br>
</code></pre>

<b>Waiting for RDV connection</b>
<pre><code>while (!rendezvous.isConnectedToRendezVous()) {<br>
	Log.d(PeerDroidSample.TAG, "Waiting for rendezvous connection!!!");<br>
	synchronized (rendezvous) {<br>
		try {<br>
			rendezvous.wait(1000);<br>
		} catch (InterruptedException e) {<br>
			e.printStackTrace();<br>
		}<br>
	}<br>
}<br>
</code></pre>

<h1>JXTA-PeerDroidSample</h1>

This is a sample application that you can use to create an heterogeneous network. JXTA-PeerDroidSample is based on standard JXTA library, but we are working to create the same level abstraction of PeerDroid in order to create in a easy way the JXTA features. For this reason you can find our service classes like JXTAService and SocketService.<br>
<br>
To start the application you must change the rdv list as we have done in the previous application. You have to go inside peerdroid.sample.service.JXTAService and change:<br>
<br>
<pre><code>private static String rdvlist = "http://www.example.com/rdvlist.txt";<br>
</code></pre>

with the list of your rdv.